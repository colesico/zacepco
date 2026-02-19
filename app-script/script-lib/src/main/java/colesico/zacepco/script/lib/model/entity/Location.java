package colesico.zacepco.script.lib.model.entity;

/**
 * Scene location
 */
public class Location extends Entity<LocationId> {

    /**
     * Hidden location or not
     */
    public Boolean hidden;

    /**
     * Location position on scene grid
     */
    public LocationPos pos;

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public LocationPos getCell() {
        return pos;
    }

    public void setCell(LocationPos pos) {
        this.pos = pos;
    }
}
