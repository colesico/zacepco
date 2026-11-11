package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;

/**
 * Clue model
 */
@YamlComment(text = {
        "Unique clue ID",
        "Format: C[A-Z0-9]{1,3}"
}, field = "id")
public final class ClueSpec extends EntitySpec<ClueSpec.Id> {

    public static final class Id extends TypedId {

        public Id(String key) {
            super(key);
        }

        @Override
        public Type type() {
            return Type.CLUE;
        }
    }
}
