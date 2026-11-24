package colesico.zacepco.investigation.model.results;

public final class InsufficientBudget extends InspectionResult {

    private InsufficientBudget() {
    }

    public static InsufficientBudget of() {
        return new InsufficientBudget();
    }
}
