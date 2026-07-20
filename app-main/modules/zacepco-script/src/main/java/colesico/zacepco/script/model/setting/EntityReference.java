package colesico.zacepco.script.model.setting;

import colesico.zacepco.script.yaml.YamlComment;

public class EntityReference {

    @YamlComment(text = "Referenced script alias")
    public String script;

    @YamlComment(text = "Referenced script entity id")
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
