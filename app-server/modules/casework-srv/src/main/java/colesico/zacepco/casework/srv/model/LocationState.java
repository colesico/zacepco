package colesico.zacepco.casework.srv.model;

import colesico.zacepco.scriptpack.model.script.Clue;
import colesico.zacepco.scriptpack.model.script.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Location state
 */
public class LocationState {

    public Location.Id id;

    /**
     * Location uncover state
     */
    public Boolean hidden = false;

    /**
     * Exposed clues for this location
     */
    public List<Clue.Id> exposedClues = new ArrayList<>();

    public LocationState() {
    }

    public LocationState(Location.Id id) {
        this.id = id;
    }

    public Location.Id getId() {
        return id;
    }

    public void setId(Location.Id id) {
        this.id = id;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public List<Clue.Id> getExposedClues() {
        return exposedClues;
    }

    public void setExposedClues(List<Clue.Id> exposedClues) {
        this.exposedClues = exposedClues;
    }
}
