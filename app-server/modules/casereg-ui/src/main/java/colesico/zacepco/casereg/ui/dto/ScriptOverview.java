package colesico.zacepco.casereg.ui.dto;

import colesico.zacepco.script.model.script.ScriptMetainfo;

import java.util.ArrayList;
import java.util.List;

public class ScriptOverview {

    public Long scriptEntryId;

    public ScriptMetainfo metainfo;

    public List<EntityOverview> personages = new ArrayList<>();

    public SceneOverview scene;

    public Long getScriptEntryId() {
        return scriptEntryId;
    }

    public void setScriptEntryId(Long scriptEntryId) {
        this.scriptEntryId = scriptEntryId;
    }

    public ScriptMetainfo getMetainfo() {
        return metainfo;
    }

    public void setMetainfo(ScriptMetainfo metainfo) {
        this.metainfo = metainfo;
    }

    public List<EntityOverview> getPersonages() {
        return personages;
    }

    public void setPersonages(List<EntityOverview> personages) {
        this.personages = personages;
    }

    public SceneOverview getScene() {
        return scene;
    }

    public void setScene(SceneOverview scene) {
        this.scene = scene;
    }
}
