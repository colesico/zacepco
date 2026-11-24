package colesico.zacepco.investigation.model.results;

public final class CluesExhausted
        extends InspectionResult {

    public Integer budgetSpending;

    private CluesExhausted() {
    }

    public static CluesExhausted of() {
        return new CluesExhausted();
    }
}
