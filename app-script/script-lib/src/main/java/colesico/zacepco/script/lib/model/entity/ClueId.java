package colesico.zacepco.script.lib.model.entity;

public final class ClueId extends EntityId {

    private ClueId(IdType type, String value) {
        super(IdType.CLUE, value);
    }

    public ClueId(String value) {
        super(IdType.CLUE, value);
    }

}
