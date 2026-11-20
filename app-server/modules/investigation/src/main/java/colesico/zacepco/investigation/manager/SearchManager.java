package colesico.zacepco.investigation.manager;

import colesico.zacepco.investigation.dto.*;
import colesico.zacepco.investigation.dto.action.*;
import colesico.zacepco.script.model.scriptdoc.*;

import java.util.*;


public class SearchManager {

    private final InvestigationManager invManager;

    private final TId entityId;

    public SearchManager(InvestigationManager invManager, LocationSpec.Id entityId) {
        this.invManager = invManager;
        this.entityId = entityId;
    }

    public SearchManager(InvestigationManager invManager, PersonageSpec.Id entityId) {
        this.invManager = invManager;
        this.entityId = entityId;
    }

    private Set<FocusedClueSpec> findUnexposedFocusedClues(Set<ClueSpec.Id> entityClues) {
        var focusedClues = invManager.script.investigation.search.clues;

        Set<FocusedClueSpec> result = new HashSet<>();
        for (var clueId : entityClues) {
            var clueState = invManager.state.clues.get(clueId);
            if (clueState.discovered) {
                continue;
            }
            var focusedClue = focusedClues.get(clueId);
            if (focusedClue == null) {
                continue;
            }
            result.add(focusedClue);
        }

        return result;
    }


    private LocationSearchResult searchClue(DetectiveFocus detectiveFocus, Integer d12) {
        Set<FocusedClueSpec> cluesToSearch = null;

        if (entityId instanceof LocationSpec.Id locationId) {
            cluesToSearch = findUnexposedFocusedClues(invManager.script.crime.scene.locations.get(locationId).clues);
        } else if (entityId instanceof PersonageSpec.Id personageId) {
            cluesToSearch = findUnexposedFocusedClues(invManager.script.crime.personages.get(personageId).clues);
        } else {
            throw new IllegalStateException("Unsupported entityId: " + entityId);
        }

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
            throw new IllegalArgumentException("Out of investigation budget" + entityId);
        }

        var locationState = invManager.state.scene.locations.get(entityId);

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