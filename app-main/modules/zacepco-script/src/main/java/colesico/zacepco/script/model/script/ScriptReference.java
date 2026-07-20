package colesico.zacepco.script.model.script;

import colesico.zacepco.script.yaml.YamlComment;

/**
 * Script reference (i.e. for include)
 */
public class ScriptReference {

    @YamlComment(text = "Referenced script ID")
    public String uuid;

    @YamlComment(text = "Local alias used to reference this script")
    public String alias;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
