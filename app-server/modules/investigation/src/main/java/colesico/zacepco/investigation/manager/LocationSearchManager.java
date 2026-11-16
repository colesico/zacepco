package colesico.zacepco.investigation.manager;

import colesico.zacepco.investigation.model.dto.DetectiveFocus;
import colesico.zacepco.investigation.model.dto.FocusedClueWeights;
import colesico.zacepco.investigation.model.dto.LocationSearchResult;
import colesico.zacepco.script.model.scriptdoc.FocusedClueSpec;
import colesico.zacepco.script.model.scriptdoc.LocationSpec;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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


    public LocationSearchResult search(DetectiveFocus detectiveFocus, Integer d12DieValue) {
        var cluesToSearch = findUnexposedFocusedClues();

        if (cluesToSearch.isEmpty()) {
            return null;
        }

        var weightedClues = FocusedClueUtil.calculateWeights(cluesToSearch, detectiveFocus);

        weightedClues.sort(
                Comparator.comparingLong(FocusedClueWeights::eq).reversed()
                        .thenComparing(Comparator.comparingLong(FocusedClueWeights::eqt).reversed())
                        .thenComparingLong(FocusedClueWeights::ne)
                        .thenComparing(Comparator.comparingDouble((FocusedClueWeights fw) -> fw.focusedClue().threshold).reversed())
        );

        double correctedD12 = d12DieValue / invManager.state.randFactor;

        // Find first accepted focusedClue
        FocusedClueWeights selected = null;
        for (var fw : weightedClues) {
            if (correctedD12 >= fw.focusedClue().threshold) {
                selected = fw;
                break;
            }
        }

        // Accidental discovery: lowest threshold among unexposed clues.
        if (selected == null) {
            var accidental = weightedClues.stream()
                    .min(Comparator.comparingDouble(fw -> fw.focusedClue().threshold))
                    .orElseThrow(() -> new IllegalStateException("No unexposed clues to search"));

            var clue = invManager.script.crime.clues.get(accidental.focusedClue().clue);
            return new LocationSearchResult(clue, true, null, null, null);
        }

        // Normal discovery: matched IDs and types computed by FocusedClueUtil
        var clue = invManager.script.crime.clues.get(selected.focusedClue().clue);
        return new LocationSearchResult(
                clue,
                false,
                selected.matchedIds(),
                selected.matchedTypes(),
                selected.unmatchedTypes()
        );
    }
}
