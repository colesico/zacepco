package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;

public final class DetectiveSpec implements ScriptReferencer {

    @YamlComment(text = {
            "Unique detective ID",
            "Format: D[A-Z0-9]{1,3}"
    })
    public Id id;

    @YamlComment(text = "Detective name")
    public String name = "";

    @YamlComment(text = {
            "Reference script ID",
            "If this detective is imported from another script, specify the ID of that reference script."
    })
    public String ref;

    @Override
    public String ref() {
        return ref;
    }

    public static final class Id extends TId {

        public Id(String key) {
            super(key);
        }

        @Override
        public Type type() {
            return Type.DETECTIVE;
        }
    }
}
