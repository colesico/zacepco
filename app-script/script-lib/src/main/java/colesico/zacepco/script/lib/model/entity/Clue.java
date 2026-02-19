package colesico.zacepco.script.lib.model.entity;

import java.util.List;

/**
 * Clue model
 */
public class Clue extends Entity<ClueId> {

    /**
     * Detailed description.
     * Entity identifiers can be specified in the description for references.
     * The identifier is specified starting with a '$' prefix.
     */
    public String description;

    /**
     * False clue or not
     */
    public Boolean falsity;

    /**
     * Entities to be open along with this clue
     */
    public List<EntityId> open;

    /**
     * Importance  assessment for the investigation
     */
    public Double importance;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFalsity() {
        return falsity;
    }

    public void setFalsity(Boolean falsity) {
        this.falsity = falsity;
    }

    public Double getImportance() {
        return importance;
    }

    public void setImportance(Double importance) {
        this.importance = importance;
    }

    public List<EntityId> getOpen() {
        return open;
    }

    public void setOpen(List<EntityId> open) {
        this.open = open;
    }
}
