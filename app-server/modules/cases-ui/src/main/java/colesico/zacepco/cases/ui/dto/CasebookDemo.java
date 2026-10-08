package colesico.zacepco.cases.ui.dto;

import colesico.zacepco.script.model.script.ScriptMetadata;

import java.util.ArrayList;
import java.util.List;

public class CasebookDemo {
    public Long caseFileId;

    public ScriptMetadata meta;

    public List<EntityDemo> locations = new ArrayList<>();

    public List<EntityDemo> personages = new ArrayList<>();

    public Long getCaseFileId() {
        return caseFileId;
    }

    public void setCaseFileId(Long caseFileId) {
        this.caseFileId = caseFileId;
    }

    public ScriptMetadata getMeta() {
        return meta;
    }

    public void setMeta(ScriptMetadata meta) {
        this.meta = meta;
    }

    public List<EntityDemo> getLocations() {
        return locations;
    }

    public void setLocations(List<EntityDemo> locations) {
        this.locations = locations;
    }

    public List<EntityDemo> getPersonages() {
        return personages;
    }

    public void setPersonages(List<EntityDemo> personages) {
        this.personages = personages;
    }
}
