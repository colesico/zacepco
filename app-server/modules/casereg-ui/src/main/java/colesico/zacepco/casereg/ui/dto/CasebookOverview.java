package colesico.zacepco.casereg.ui.dto;

import colesico.zacepco.script.model.script.ScriptMetainfo;

import java.util.ArrayList;
import java.util.List;

public class CasebookOverview {

    public Long casebookId;

    public ScriptMetainfo metainfo;

    public List<EntityOverview> personages = new ArrayList<>();

    public SceneOverview scene;

    public Long getCasebookId() {
        return casebookId;
    }

    public void setCasebookId(Long casebookId) {
        this.casebookId = casebookId;
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
