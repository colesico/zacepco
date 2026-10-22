package colesico.zacepco.scriptpack.model.script;

public final class TimeId extends Entity.UID {

    public TimeId(String key) {
        super(key);
    }

    @Override
    public Entity.Type type() {
        return Entity.Type.TIME;
    }

}
