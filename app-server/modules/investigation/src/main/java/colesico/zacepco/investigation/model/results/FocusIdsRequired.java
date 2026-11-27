package colesico.zacepco.investigation.model.results;

public class FocusIdsRequired extends InspectionResult {
    /**
     * Require minimum detective focus IDs
     */
    public final Integer minItems;

    public FocusIdsRequired(Integer minItems) {
        this.minItems = minItems;
    }

    public static FocusIdsRequired of(Integer minItems) {
        return new FocusIdsRequired(minItems);
    }
}
