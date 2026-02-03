package colesico.zacepco.script.lib.model;

import java.util.List;

/**
 * Location model
 */
public class Location {

    public EntityId id;

    public String name;

    /**
     * Location description
     */
    public String description;

    /**
     * General passages.
     * Location IDs where the passages lead from this location
     */
    public List<EntityId> passages;

    /**
     * Hidden passages.
     * Location IDs where the passages lead from this location
     */
    public List<EntityId> hiddenPassages;

    /**
     * Hidden location or not
     */
    public Boolean hidden;

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

    public List<EntityId> getPassages() {
        return passages;
    }

    public void setPassages(List<EntityId> passages) {
        this.passages = passages;
    }

    public List<EntityId> getHiddenPassages() {
        return hiddenPassages;
    }

    public void setHiddenPassages(List<EntityId> hiddenPassages) {
        this.hiddenPassages = hiddenPassages;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }
}
