package colesico.zacepco.investigation.model;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;

import java.util.ArrayList;
import java.util.List;

/**
 * Location state
 */
public class LocationState {

    public String id;

    @Stateless
    public String title;

    @Stateless
    public String description;

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

    public LocationState(String id) {
        this.id = id;
    }

}
