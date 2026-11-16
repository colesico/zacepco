package colesico.zacepco.investigation.model.state;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;
import colesico.zacepco.script.model.scriptdoc.LocationSpec;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
     * Whether the location can still be searched.
     * True if either no search has been performed in the location yet,
     * or a search has been performed and no unexposed clues remain in the location.
     */
    public boolean searchAllowed = true;

    /**
     * Exposed clues for this location
     */
    public Set<ClueSpec.Id> exposedClues = new LinkedHashSet<>();


    public LocationState() {
    }

    public LocationState(LocationSpec.Id id) {
        this.id = id;
    }
}
