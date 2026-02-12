package colesico.zacepco.script.lib.model.entity;

/**
 * Clue model
 */
public class Clue extends Entity {

    /**
     * Detailed description.
     * Entity identifiers can be specified in the description for references.
     * The identifier is specified starting with a '$' prefix.
     */
    public String details;

    /**
     * False artifact - unrelated to the crime
     */
    public Boolean unrelated;

    /**
     * Importance for the investigation
     */
    public Double importance;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean getUnrelated() {
        return unrelated;
    }

    public void setUnrelated(Boolean unrelated) {
        this.unrelated = unrelated;
    }

    public Double getImportance() {
        return importance;
    }

    public void setImportance(Double importance) {
        this.importance = importance;
    }
}
