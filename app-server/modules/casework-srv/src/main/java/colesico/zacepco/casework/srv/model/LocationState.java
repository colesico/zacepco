package colesico.zacepco.casework.srv.model;

import colesico.zacepco.scriptpack.model.script.Location;

/**
 * Location state
 */

public class LocationState {

    public Location.Id locationId;

    /**
     * Location  discovered state.
     * For hidden location that not discovered yet = true
     */
    public Boolean hidden = false;

    /**
     * Clues that not discovered yet for this location
     */
    public Integer cluesToDiscover;

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }
}
