package colesico.zacepco.respack.model.script;

public class Detective extends Entity<Detective.Id> {

    public static final class Id extends UID {

        public Id(String key) {
            super(key);
        }

        @Override
        public Entity.Type type() {
            return Entity.Type.CLUE;
        }
    }
}
