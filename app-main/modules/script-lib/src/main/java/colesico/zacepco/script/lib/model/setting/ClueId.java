package colesico.zacepco.script.lib.model.setting;

public final class ClueId extends EntityId {

    private ClueId(EntityType type, String value) {
        super(EntityType.CLUE, value);
    }

    public ClueId(String value) {
        super(EntityType.CLUE, value);
    }

}
