package colesico.zacepco.script.model.setting;

/**
 * Scene location
 */
public class Location extends Entity<LocationId> {

    /**
     * Location position on scene grid
     */
    public LocationPos pos;

    /**
     * Hidden location or not
     */
    public Boolean hidden;

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public LocationPos getPos() {
        return pos;
    }

    public void setPos(LocationPos pos) {
        this.pos = pos;
    }
}
