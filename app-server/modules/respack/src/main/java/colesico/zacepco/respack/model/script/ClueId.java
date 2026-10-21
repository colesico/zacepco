package colesico.zacepco.respack.model.script;

public final class ClueId extends EntityId {

    private ClueId(EntityType type, String value) {
        super(EntityType.CLUE, value);
    }

    public ClueId(String value) {
        super(EntityType.CLUE, value);
    }

}
