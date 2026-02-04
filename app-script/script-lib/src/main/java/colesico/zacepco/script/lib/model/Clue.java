package colesico.zacepco.script.lib.model;

import java.util.Map;

/**
 * Artifact model
 */
public class Clue {

    public EntityId id;

    public String name;

    public String description;

    /**
     * Artifact parameters
     */
    public Map<String,EntityId> params;

    /**
     * Is artifact unrelated to the crime
     */
    public Boolean unrelated;

    /**
     * Is artifact custom
     */
    public Boolean custom;

    public EntityId getId() {
        return id;
    }

    public void setId(EntityId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, EntityId> getParams() {
        return params;
    }

    public void setParams(Map<String, EntityId> params) {
        this.params = params;
    }

    public Boolean getUnrelated() {
        return unrelated;
    }

    public void setUnrelated(Boolean unrelated) {
        this.unrelated = unrelated;
    }

    public Boolean getCustom() {
        return custom;
    }

    public void setCustom(Boolean custom) {
        this.custom = custom;
    }
}
