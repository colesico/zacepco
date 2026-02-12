package colesico.zacepco.script.lib.model.entity;

import java.util.List;

/**
 * Location model
 */
public class Location extends Entity {

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

    /**
     * Location position on scene grid
     */
    public Cell cell;

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
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
