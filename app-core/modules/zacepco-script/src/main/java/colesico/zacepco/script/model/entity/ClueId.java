package colesico.zacepco.script.model.entity;

public final class ClueId extends EntityId {

    private ClueId(EntityType type, String value) {
        super(EntityType.CLUE, value);
    }

    public ClueId(String value) {
        super(EntityType.CLUE, value);
    }

}
