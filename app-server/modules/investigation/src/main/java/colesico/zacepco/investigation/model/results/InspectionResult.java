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

    public static InspectionResult of() {
        return new InspectionResult() {
        };
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

    public InspectionResult inspectionType(InspectionTypeSpec inspectionType) {
        this.inspectionType = inspectionType;
        return this;
    }

    public InspectionResult inspectionTarget(TId inspectionTarget) {
        this.inspectionTarget = inspectionTarget;
        return this;
    }

    public InspectionResult activeDetective(DetectiveState activeDetective) {
        this.activeDetective = activeDetective;
        return this;
    }

    public InspectionResult detectiveFocus(DetectiveFocus detectiveFocus) {
        this.detectiveFocus = detectiveFocus;
        return this;
    }

    public InspectionResult d12(Integer d12) {
        this.d12 = d12;
        return this;
    }

    public InspectionResult budgetSpend(Integer budgetSpend) {
        this.budgetSpend = budgetSpend;
        return this;
    }

    public InspectionResult currentBudget(Integer currentBudget) {
        this.currentBudget = currentBudget;
        return this;
    }
}
