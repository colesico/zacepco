package colesico.zacepco.scriptpack.model.script;

public class Detective extends Entity<Detective.Id> {

    @Override
    public void setId(Id id) {
        super.setId(id);
    }

    public static final class Id extends UID {

        public Id(String key) {
            super(key);
        }

        @Override
        public Entity.Type type() {
            return Type.DETECTIVE;
        }
    }
}
