package colesico.zacepco.script.lib.model.entity;

public class Passage {

    /**
     * Related locations
     */
    public LocationId from;
    public LocationId to;

    /**
     * Is hidden passage
     */
    public boolean hidden;

    public LocationId getFrom() {
        return from;
    }

    public void setFrom(LocationId from) {
        this.from = from;
    }

    public LocationId getTo() {
        return to;
    }

    public void setTo(LocationId to) {
        this.to = to;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
