package colesico.zacepco.script.model.script;

import colesico.zacepco.script.yaml.YamlComment;

/**
 * Script reference (i.e. for include)
 */
public class ScriptReference {

    @YamlComment(text = "Referenced script ID (target script)")
    public String id;

    @YamlComment(text = "Local alias used to reference this script")
    public String alias;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
