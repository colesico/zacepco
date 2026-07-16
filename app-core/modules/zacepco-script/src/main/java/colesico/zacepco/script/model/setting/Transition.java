package colesico.zacepco.script.model.setting;

import colesico.zacepco.script.yaml.YamlComment;

/**
 * Transition between two locations
 */
public class Transition {

    /**
     * Related locations
     */
    @YamlComment(text = "One location id")
    public LocationId from;

    @YamlComment(text = "Other location id")
    public LocationId to;

    /**
     * Is hidden passage
     */
    @YamlComment(text = "Is hidden transition or not (default - false)")
    public Boolean hidden;

    public boolean hidden() {
        return hidden != null ? hidden : false;
    }

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

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }
}
