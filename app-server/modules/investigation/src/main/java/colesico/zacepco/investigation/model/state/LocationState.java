package colesico.zacepco.investigation.model.state;

import colesico.zacepco.script.model.scriptdoc.LocationSpec;

/**
 * Location state
 */
public class LocationState implements Searchable {

    public LocationSpec.Id id;

    /**
     * Location uncover state
     */
    public boolean hidden = false;

    /**
     * Whether the location can still be searched.
     * True if no search has been performed yet,
     * or if a search has been performed and undiscovered clues still remain.
     * Set to {@code false} once all clues have been discovered.
     */
    public boolean searchAvailable = true;

    public LocationState() {
    }

    public LocationState(LocationSpec.Id id) {
        this.id = id;
    }

    @Override
    public boolean searchAvailable() {
        return searchAvailable;
    }

    @Override
    public void makeUnsearchable() {
        searchAvailable = false;
    }
}
