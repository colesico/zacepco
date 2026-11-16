package colesico.zacepco.investigation.manager;

import colesico.zacepco.investigation.dto.DetectiveFocus;
import colesico.zacepco.script.model.scriptdoc.ClueSpec;
import colesico.zacepco.script.model.scriptdoc.FocusedClueSpec;
import colesico.zacepco.script.model.scriptdoc.LocationSpec;

import java.util.ArrayList;
import java.util.List;

public class LocationSearchManager {

    private final InvestigationManager invManager;

    public LocationSearchManager(InvestigationManager invManager) {
        this.invManager = invManager;
    }

    private List<FocusedClueSpec> findUnexposedFocusedClues(LocationSpec.Id locationId) {
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
        return null;
    }
}
