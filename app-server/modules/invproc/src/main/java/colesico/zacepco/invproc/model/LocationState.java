package colesico.zacepco.invproc.model;

import colesico.zacepco.scriptpack.model.script.ClueSpec;

import java.util.ArrayList;
import java.util.List;

/**
 * Location state
 */
public class LocationState {

    public String id;

    /**
     * Location uncover state
     */
    public boolean hidden = false;

    /**
     * Exposed clues for this location
     */
    public List<ClueSpec.Id> exposedClues = new ArrayList<>();

    public LocationState(String id) {
        this.id = id;
    }

}
