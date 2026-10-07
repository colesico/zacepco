package colesico.zacepco.cases.ui.dto;

import colesico.zacepco.script.model.script.ScriptMetadata;

import java.util.ArrayList;
import java.util.List;

public class CaseFileDetails {
    public Long caseFileId;

    public ScriptMetadata meta;

    public List<EntityDetails> locations = new ArrayList<>();

    public List<EntityDetails> personages = new ArrayList<>();

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

    public List<EntityDetails> getLocations() {
        return locations;
    }

    public void setLocations(List<EntityDetails> locations) {
        this.locations = locations;
    }

    public List<EntityDetails> getPersonages() {
        return personages;
    }

    public void setPersonages(List<EntityDetails> personages) {
        this.personages = personages;
    }
}
