package colesico.zacepco.casereg.ui.dto;

import colesico.zacepco.script.model.script.ScriptMeta;

import java.util.ArrayList;
import java.util.List;

public class CasebookOverview {

    public Long casebookId;

    public ScriptMeta meta;

    public List<EntityOverview> personages = new ArrayList<>();

    public SceneOverview scene;

    public Long getCasebookId() {
        return casebookId;
    }

    public void setCasebookId(Long casebookId) {
        this.casebookId = casebookId;
    }

    public ScriptMeta getMeta() {
        return meta;
    }

    public void setMeta(ScriptMeta meta) {
        this.meta = meta;
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
