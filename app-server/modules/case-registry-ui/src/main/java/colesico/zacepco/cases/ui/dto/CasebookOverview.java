package colesico.zacepco.cases.ui.dto;

import colesico.zacepco.script.model.script.ScriptMeta;

import java.util.ArrayList;
import java.util.List;

public class CasebookOverview {

    public Long casebookId;

    public ScriptMeta meta;

    public List<EntityOverview> locations = new ArrayList<>();

    public List<EntityOverview> personages = new ArrayList<>();

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

    public List<EntityOverview> getLocations() {
        return locations;
    }

    public void setLocations(List<EntityOverview> locations) {
        this.locations = locations;
    }

    public List<EntityOverview> getPersonages() {
        return personages;
    }

    public void setPersonages(List<EntityOverview> personages) {
        this.personages = personages;
    }
}
