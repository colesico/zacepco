package colesico.zacepco.script.spec.model.entity;

public final class ClueId extends EntityId {

    private ClueId(IdType type, String value) {
        super(IdType.CLUE, value);
    }

    public ClueId(String value) {
        super(IdType.CLUE, value);
    }

}
