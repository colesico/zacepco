package colesico.zacepco.script.model.entity;

public final class ClueId extends EntityId {

    private ClueId(IdType type, String value) {
        super(IdType.CLUE, value);
    }

    public ClueId(String value) {
        super(IdType.CLUE, value);
    }

}
