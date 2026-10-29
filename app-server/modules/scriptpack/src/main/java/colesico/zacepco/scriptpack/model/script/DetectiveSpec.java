package colesico.zacepco.scriptpack.model.script;

public class DetectiveSpec extends EntitySpec<DetectiveSpec.Id> {

    @Override
    public void setId(Id id) {
        super.setId(id);
    }

    public static final class Id extends UID {

        public Id(String key) {
            super(key);
        }

        @Override
        public EntitySpec.Type type() {
            return Type.DETECTIVE;
        }
    }
}
