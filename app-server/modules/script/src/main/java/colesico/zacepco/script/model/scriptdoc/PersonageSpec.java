package colesico.zacepco.script.model.scriptdoc;

/**
 * Game character
 */
public final class PersonageSpec extends EntitySpec<PersonageSpec.Id> {

    public static final class Id extends UID {

        @Override
        public Type type() {
            return Type.PERSONAGE;
        }

        public Id(String value) {
            super(value);
        }
    }
}
