package colesico.zacepco.investigation.model.results;

import colesico.zacepco.investigation.model.DetectiveFocus;
import colesico.zacepco.investigation.model.state.DetectiveState;
import colesico.zacepco.script.model.scriptdoc.InspectionTypeSpec;
import colesico.zacepco.script.model.scriptdoc.TId;

abstract public class InspectionResult {

    public InspectionTypeSpec inspectionType;

    public TId inspectionTarget;

    public DetectiveState activeDetective;

    public DetectiveFocus detectiveFocus;

    public Integer d12;

    public Integer budgetSpend;

    public Integer currentBudget;

    public InspectionResult() {
    }

    public InspectionResult assign(InspectionResult values) {
        this.inspectionType = values.inspectionType;
        this.inspectionTarget = values.inspectionTarget;
        this.activeDetective = values.activeDetective;
        this.detectiveFocus = values.detectiveFocus;
        this.d12 = values.d12;
        this.budgetSpend = values.budgetSpend;
        this.currentBudget = values.currentBudget;
        return this;
    }
}
