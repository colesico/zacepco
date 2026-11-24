package colesico.zacepco.investigation.model.results;

import colesico.zacepco.investigation.model.DetectiveFocus;
import colesico.zacepco.investigation.model.InspectionType;
import colesico.zacepco.investigation.model.state.DetectiveState;
import colesico.zacepco.script.model.scriptdoc.EntitySpec;

abstract public class InspectionResult {

    public InspectionType inspectionType;

    public EntitySpec inspectionSubject;

    public DetectiveFocus detectiveFocus;

    public Integer d12;

    public DetectiveState activeDetective;

    public InspectionResult() {
    }

    public InspectionResult assign(InspectionResult values) {
        this.inspectionType = values.inspectionType;
        this.inspectionSubject = values.inspectionSubject;
        this.detectiveFocus = values.detectiveFocus;
        this.d12 = values.d12;
        this.activeDetective = values.activeDetective;
        return this;
    }
}
