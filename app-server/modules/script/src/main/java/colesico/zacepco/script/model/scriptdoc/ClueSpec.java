package colesico.zacepco.script.model.scriptdoc;

/**
 * Clue model
 */
public final class ClueSpec extends EntitySpec<ClueSpec.Id> {

    public static final class Id extends TId {

        public Id(String key) {
            super(key);
        }

        @Override
        public Type type() {
            return Type.CLUE;
        }
    }
}
