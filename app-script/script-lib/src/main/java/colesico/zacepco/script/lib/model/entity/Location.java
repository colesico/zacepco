package colesico.zacepco.script.lib.model.entity;

/**
 * Location model
 */
public class Location extends Entity<LocationId> {

    /**
     * Location description
     */
    public String description;

    /**
     * Hidden location or not
     */
    public Boolean hidden;

    /**
     * Location position on scene grid
     */
    public Pos pos;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Pos getCell() {
        return pos;
    }

    public void setCell(Pos pos) {
        this.pos = pos;
    }
}
