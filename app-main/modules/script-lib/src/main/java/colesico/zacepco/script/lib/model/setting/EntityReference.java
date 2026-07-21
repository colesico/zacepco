package colesico.zacepco.script.lib.model.setting;

import colesico.zacepco.script.lib.yaml.YamlComment;

public class EntityReference {

    @YamlComment(text = "Referenced script alias (target script)")
    public String script;

    @YamlComment(text = "Referenced script entity id (target entity)")
    public EntityId entity;

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public EntityId getEntity() {
        return entity;
    }

    public void setEntity(EntityId entity) {
        this.entity = entity;
    }
}
