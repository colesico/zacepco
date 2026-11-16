package colesico.zacepco.investigation.manager;

import colesico.zacepco.investigation.dto.DetectiveFocus;
import colesico.zacepco.investigation.model.data.FocusedClueWeights;
import colesico.zacepco.script.model.scriptdoc.ClueSpec;
import colesico.zacepco.script.model.scriptdoc.FocusedClueSpec;
import colesico.zacepco.script.model.scriptdoc.LocationSpec;

import java.util.ArrayList;
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


    public ClueSpec search(DetectiveFocus detectiveFocus, Integer d12DieValue) {
        var cluesToSearch = findUnexposedFocusedClues();
        var weightedClues = FocusedClueWeightsCalc.calculate(cluesToSearch, detectiveFocus);
    }
}
