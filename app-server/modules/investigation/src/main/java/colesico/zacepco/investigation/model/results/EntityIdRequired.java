package colesico.zacepco.investigation.model.results;

public class EntityIdRequired extends InspectionResult {
    /**
     * Require minimum detective focus IDs
     */
    public final Integer minItems;

    public EntityIdRequired(Integer minItems) {
        this.minItems = minItems;
    }

    public static EntityIdRequired of(Integer minItems) {
        return new EntityIdRequired(minItems);
    }
}
