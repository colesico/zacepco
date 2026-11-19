package colesico.zacepco.investigation.manager;

import colesico.zacepco.investigation.dto.*;
import colesico.zacepco.investigation.dto.action.*;
import colesico.zacepco.script.model.scriptdoc.FocusedClueSpec;
import colesico.zacepco.script.model.scriptdoc.LocationSpec;

import java.util.*;


public class LocationSearchManager {

    private final InvestigationManager invManager;

    private final LocationSpec.Id locationId;

    public LocationSearchManager(InvestigationManager invManager, LocationSpec.Id locationId) {
        this.invManager = invManager;
        this.locationId = locationId;
    }

    private Set<FocusedClueSpec> findUnexposedFocusedClues() {
        Set<FocusedClueSpec> result = new HashSet<>();
        var locationSpec = invManager.script.crime.scene.locations.get(locationId);
        var focusedClues = invManager.script.investigation.search.clues;

        for (var clueId : locationSpec.clues) {
            var clueState = invManager.state.clues.get(clueId);
            if (clueState.discovered) {
                continue;
            }
            var focusedClue = focusedClues.get(clueId);
            if (focusedClue == null){
                throw new IllegalStateException("Search focused clue not found: "+clueId);
            }
            result.add(focusedClue);
        }

        return result;
    }


    private LocationSearchResult searchClue(DetectiveFocus detectiveFocus, Integer d12) {
        var cluesToSearch = findUnexposedFocusedClues();

        if (cluesToSearch.isEmpty()) {
            return new CluesExhausted();
        }

        var matchedClues = InvestigationUtil.matchClues(cluesToSearch, detectiveFocus);

        // Order: EQ desc, EQT desc, NE asc, threshold desc
        matchedClues.sort(
                Comparator.comparingLong(FocusedClueMatch::eq).reversed()
                        .thenComparing(Comparator.comparingLong(FocusedClueMatch::eqt).reversed())
                        .thenComparingLong(FocusedClueMatch::ne)
                        .thenComparing(Comparator.comparingDouble(
                                (FocusedClueMatch fw) -> fw.focusedClue().threshold).reversed())
        );

        double rf = invManager.state.randFactor;

        // Find first accepted
        FocusedClueMatch selected = null;
        for (var mc : matchedClues) {
            if ((mc.eq() > 0 || mc.eqt() > 0) && (d12 >= mc.focusedClue().threshold * rf)) {
                selected = mc;
                break;
            }
        }

        // Accidental discovery: lowest threshold among unexposed clues.
        if (selected == null) {
            var accidental = matchedClues.stream()
                    .min(Comparator.comparingDouble(fw -> fw.focusedClue().threshold))
                    .orElseThrow();

            var clue = invManager.script.crime.clues.get(accidental.focusedClue().clue);
            return new ClueUncover(clue, matchedClues.size() - 1, 0);
        }

        // Focus matched discovery
        var clue = invManager.script.crime.clues.get(selected.focusedClue().clue);
        return new ClueFocusedUncover(
                clue,
                selected.matchedIds(),
                selected.matchedTypes(),
                selected.unmatchedTypes(),
                matchedClues.size() - 1,
                0
        );
    }

    public LocationSearchResult search(DetectiveFocus detectiveFocus, Integer d12) {

        if (invManager.state.budget <= 0) {
            throw new IllegalArgumentException("Out of investigation budget" + locationId);
        }

        var locationState = invManager.state.scene.locations.get(locationId);

        if (!locationState.searchAvailable) {
            throw new IllegalArgumentException("Search not available");
        }

        // Check params
        var res = InvestigationUtil.checkActionRequest(detectiveFocus, d12, invManager.script, invManager.state);
        if (res != null) return res;

        // Try to spend budget
        var cost = invManager.script.investigation.search.cost;
        if (!invManager.state.spendBudget(cost)) {
            return new InsufficientBudget();
        }

        // Perform clue search
        var result = searchClue(detectiveFocus, d12);
        
        if (result instanceof CluesExhausted) {

            locationState.searchAvailable = false;
            invManager.state.nextDetective();

        } else if (result instanceof ClueUncover cuResult) {

            invManager.state.clues.get(cuResult.clue.id).discovered = true;

            if (cuResult.cluesRemaining == 0) {
                locationState.searchAvailable = false;
            }

            // Detective awarded points
            if (result instanceof ClueFocusedUncover cfuResult) {
                var detective = invManager.state.activeDetective();
                var awardedPoints = invManager.script.investigation.search.points;
                awardedPoints = awardedPoints + cfuResult.matchedIds.size();
                detective.addPoints(awardedPoints);
                cfuResult.awardedPoints = awardedPoints;
            }

            // Shift to next detective
            invManager.state.nextDetective();
        }

        return result;
    }


}