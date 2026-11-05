package colesico.zacepco.investigation.service;

import colesico.zacepco.investigation.model.DetectiveFocus;
import colesico.zacepco.script.model.scriptdoc.ClueSpec;

public class LocationSearchManager {

    private final InvestigationManager investigationManager;

    public LocationSearchManager(InvestigationManager investigationManager) {
        this.investigationManager = investigationManager;
    }

    public ClueSpec search(DetectiveFocus detectiveFocus, Integer d12DieValue) {
        return null;
    }
}
