package colesico.zacepco.script.model.scriptdoc;

public final class DetectiveSpec extends EntitySpec<DetectiveSpec.Id> {

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
