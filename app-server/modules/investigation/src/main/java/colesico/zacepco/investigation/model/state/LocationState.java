package colesico.zacepco.investigation.model.state;

import colesico.zacepco.script.model.scriptdoc.LocationSpec;

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
     * True if no search has been performed in the location yet,
     * or if a search has been performed and unexposed clues still remain.
     * Set to {@code false} once all clues in the location have been exposed.
     */
    public boolean searchAvailable = true;

    public LocationState() {
    }

    public LocationState(LocationSpec.Id id) {
        this.id = id;
    }
}
