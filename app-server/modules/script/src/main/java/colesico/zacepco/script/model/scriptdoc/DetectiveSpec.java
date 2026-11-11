package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;

@YamlComment(text = {
        "Unique detective ID",
        "Format: D[A-Z0-9]{1,3}"
}, field = "id")
public final class DetectiveSpec extends EntitySpec<DetectiveSpec.Id> {

    public static final class Id extends TypedId {

        public Id(String key) {
            super(key);
        }

        @Override
        public Type type() {
            return Type.DETECTIVE;
        }
    }
}
