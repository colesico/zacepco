package colesico.zacepco.script.model.entity;

public final class TimeId extends EntityId {

    private TimeId(IdType type, String value) {
        super(type, value);
    }

    public TimeId(String value) {
        super(IdType.TIME, value);
    }

}
