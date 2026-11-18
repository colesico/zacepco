package colesico.zacepco.investigation.manager;

import colesico.zacepco.investigation.dto.DetectiveFocus;
import colesico.zacepco.investigation.dto.FocusedClueWeights;
import colesico.zacepco.investigation.dto.LocationSearchResult;
import colesico.zacepco.script.model.scriptdoc.FocusedClueSpec;
import colesico.zacepco.script.model.scriptdoc.LocationSpec;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static colesico.zacepco.investigation.dto.InvestigationActionResult.Status.*;

public class LocationSearchManager {

    private final InvestigationManager invManager;

    private final LocationSpec.Id locationId;

    public LocationSearchManager(InvestigationManager invManager, LocationSpec.Id locationId) {
        this.invManager = invManager;
        this.locationId = locationId;
    }

    private List<FocusedClueSpec> findUnexposedFocusedClues() {
        List<FocusedClueSpec> result = new ArrayList<>();
        var locationState = invManager.state.scene.locations.get(locationId);
        var searchSpec = invManager.script.investigation.search.locations.get(locationId);

        for (var focusedClueKV : searchSpec.clues.entrySet()) {
            var focusedClueId = focusedClueKV.getKey();
            if (locationState.exposedClues.contains(focusedClueId)) {
                continue;
            }
            result.add(focusedClueKV.getValue());
        }
        return result;
    }


    private LocationSearchResult searchClue(DetectiveFocus detectiveFocus, Integer d12DieValue) {
        var cluesToSearch = findUnexposedFocusedClues();

        if (cluesToSearch.isEmpty()) {
            return LocationSearchResult.error(CLUES_EXHAUSTED);
        }

        var weightedClues = FocusedClueUtil.calculateWeights(cluesToSearch, detectiveFocus);

        // Order: EQ desc, EQT desc, NE asc, threshold desc
        weightedClues.sort(
                Comparator.comparingLong(FocusedClueWeights::eq).reversed()
                        .thenComparing(Comparator.comparingLong(FocusedClueWeights::eqt).reversed())
                        .thenComparingLong(FocusedClueWeights::ne)
                        .thenComparing(Comparator.comparingDouble(
                                (FocusedClueWeights fw) -> fw.focusedClue().threshold).reversed())
        );

        double rf = invManager.state.randFactor;

        // Find first accepted focusedClue
        FocusedClueWeights selected = null;
        for (var fw : weightedClues) {
            if (d12DieValue >= fw.focusedClue().threshold * rf) {
                selected = fw;
                break;
            }
        }

        // Accidental discovery: lowest threshold among unexposed clues.
        if (selected == null) {
            var accidental = weightedClues.stream()
                    .min(Comparator.comparingDouble(fw -> fw.focusedClue().threshold))
                    .orElseThrow();

            var clue = invManager.script.crime.clues.get(accidental.focusedClue().clue);
            return LocationSearchResult.accidentalClue(clue, weightedClues.size() - 1);
        }

        // Focus matched discovery
        var clue = invManager.script.crime.clues.get(selected.focusedClue().clue);
        return LocationSearchResult.matchedClue(
                clue,
                selected.matchedIds(),
                selected.matchedTypes(),
                selected.unmatchedTypes(),
                weightedClues.size() - 1
        );
    }

    public LocationSearchResult search(DetectiveFocus detectiveFocus, Integer d12DieValue) {

        // Check params

        if (!D12Utils.check(d12DieValue)) {
            return LocationSearchResult.error(INVALID_D12_VALUE);
        }

        // Check budget and spend

        var cost = invManager.script.investigation.search.cost;
        if (!invManager.state.spendBudget(cost)) {
            return LocationSearchResult.error(INSUFFICIENT_BUDGET);
        }

        // Perform clue search

        var result = searchClue(detectiveFocus, d12DieValue);

        if (!result.isOK()) {
            return result;
        }

        // Update location state

        var locationState = invManager.state.scene.locations.get(locationId);

        locationState.exposedClues.add(result.clue.id);

        if (result.cluesRemaining == 0) {
            locationState.searchAvailable = false;
        }

        // Detective awarded points
        if (!result.accidental) {
            var detective = invManager.state.activeDetective();
            var awardedPoints = invManager.script.investigation.search.points;
            awardedPoints = awardedPoints + result.matchedIds.size();
            detective.addPoints(awardedPoints);
            result.awardedPoints = awardedPoints;
        }

        // Shift to next detective
        invManager.state.nextDetective();

        return result;
    }
}