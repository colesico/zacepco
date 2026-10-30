package colesico.zacepco.invproc.model;

import colesico.zacepco.scriptpack.model.script.ClueSpec;
import colesico.zacepco.scriptpack.model.script.LocationSpec;

import java.util.ArrayList;
import java.util.List;

/**
 * Location state
 */
public class LocationState {

    public LocationSpec.Id id;

    /**
     * Location uncover state
     */
    public boolean hidden = false;

    /**
     * Exposed clues for this location
     */
    public List<ClueSpec.Id> exposedClues = new ArrayList<>();

    public LocationState() {
    }

    public LocationState(LocationSpec.Id id) {
        this.id = id;
    }

    public LocationSpec.Id getId() {
        return id;
    }

    public void setId(LocationSpec.Id id) {
        this.id = id;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public List<ClueSpec.Id> getExposedClues() {
        return exposedClues;
    }

    public void setExposedClues(List<ClueSpec.Id> exposedClues) {
        this.exposedClues = exposedClues;
    }
}
