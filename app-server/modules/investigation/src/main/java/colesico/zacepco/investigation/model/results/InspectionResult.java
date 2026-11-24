package colesico.zacepco.investigation.model.results;

import colesico.zacepco.investigation.model.DetectiveFocus;
import colesico.zacepco.investigation.model.InspectionType;
import colesico.zacepco.investigation.model.state.DetectiveState;
import colesico.zacepco.script.model.scriptdoc.TId;

abstract public class InspectionResult {

    public InspectionType inspectionType;

    public TId inspectionSubjectId;

    public DetectiveFocus detectiveFocus;

    public Integer d12;

    public DetectiveState activeDetective;

    public InspectionResult() {
    }

    public InspectionResult assign(InspectionResult values) {
        this.inspectionType = values.inspectionType;
        this.inspectionSubjectId = values.inspectionSubjectId;
        this.detectiveFocus = values.detectiveFocus;
        this.d12 = values.d12;
        this.activeDetective = values.activeDetective;
        return this;
    }
}
