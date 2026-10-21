package colesico.zacepco.respack.model.script;

import colesico.zacepco.respack.yaml.YamlComment;

/**
 * Unidirectional connection between two locations
 */
public class Connection {

    /**
     * Related locations
     */
    @YamlComment(text = "One location id")
    public Location.Id locationU;

    @YamlComment(text = "Other location id")
    public Location.Id locationV;

    /**
     * Is hidden passage
     */
    @YamlComment(text = "Is hidden transition or not (default - false)")
    public Boolean hidden;

    public boolean isHidden() {
        return hidden != null ? hidden : false;
    }

    public Location.Id getLocationU() {
        return locationU;
    }

    public void setLocationU(Location.Id locationU) {
        this.locationU = locationU;
    }

    public Location.Id getLocationV() {
        return locationV;
    }

    public void setLocationV(Location.Id locationV) {
        this.locationV = locationV;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }
}
