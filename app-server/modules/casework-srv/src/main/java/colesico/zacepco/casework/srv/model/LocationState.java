package colesico.zacepco.casework.srv.model;

import colesico.zacepco.scriptpack.model.script.Clue;
import colesico.zacepco.scriptpack.model.script.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Location state
 */
public class LocationState {

    public Location.Id locationId;

    /**
     * Location discover state
     */
    public Boolean hidden = false;

    /**
     * Discovered clues for this location
     */
    public List<Clue.Id> discoveredClues = new ArrayList<>();

    public Location.Id getLocationId() {
        return locationId;
    }

    public void setLocationId(Location.Id locationId) {
        this.locationId = locationId;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public List<Clue.Id> getDiscoveredClues() {
        return discoveredClues;
    }

    public void setDiscoveredClues(List<Clue.Id> discoveredClues) {
        this.discoveredClues = discoveredClues;
    }
}
