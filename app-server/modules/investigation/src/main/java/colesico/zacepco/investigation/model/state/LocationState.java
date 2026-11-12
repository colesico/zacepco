package colesico.zacepco.investigation.model.state;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;
import colesico.zacepco.script.model.scriptdoc.LocationSpec;

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
}
