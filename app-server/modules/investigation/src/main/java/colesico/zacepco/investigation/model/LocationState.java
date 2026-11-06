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
    public String name;

    @Stateless
    public String description;

    @Stateless
    public String ref;

    @Stateless
    public LocationPos pos;

    /**
     * Location uncover state
     */
    public boolean hidden = false;

    /**
     * Exposed clues for this location
     */
    public List<String> exposedClues = new ArrayList<>();

    public LocationState() {
    }

    public LocationState(String id) {
        this.id = id;
    }

}
