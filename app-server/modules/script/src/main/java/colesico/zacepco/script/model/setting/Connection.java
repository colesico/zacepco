package colesico.zacepco.script.model.setting;

import colesico.zacepco.script.yaml.YamlComment;

/**
 * Unidirectional connection between two locations
 */
public class Connection {

    /**
     * Related locations
     */
    @YamlComment(text = "One location id")
    public LocationId locationU;

    @YamlComment(text = "Other location id")
    public LocationId locationV;

    /**
     * Is hidden passage
     */
    @YamlComment(text = "Is hidden transition or not (default - false)")
    public Boolean hidden;

    public boolean isHidden() {
        return hidden != null ? hidden : false;
    }

    public LocationId getLocationU() {
        return locationU;
    }

    public void setLocationU(LocationId locationU) {
        this.locationU = locationU;
    }

    public LocationId getLocationV() {
        return locationV;
    }

    public void setLocationV(LocationId locationV) {
        this.locationV = locationV;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }
}
