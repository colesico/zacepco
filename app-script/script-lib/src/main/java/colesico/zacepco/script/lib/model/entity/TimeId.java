package colesico.zacepco.script.lib.model.entity;

public final class TimeId extends EntityId {

    private TimeId(IdType type, String value) {
        super(type, value);
    }

    public TimeId(String value) {
        super(IdType.TIME, value);
    }

}
