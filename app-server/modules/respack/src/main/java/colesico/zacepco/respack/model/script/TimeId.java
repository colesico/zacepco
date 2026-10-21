package colesico.zacepco.respack.model.script;

public final class TimeId extends Entity.TypedId {

    public TimeId(String key) {
        super(key);
    }

    @Override
    public Entity.Type type() {
        return Entity.Type.TIME;
    }

}
