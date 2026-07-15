package colesico.zacepco.script.model.investigation;

import java.util.List;

/**
 * Search specification
 */
public final class Search extends InvestigationAction {

    /**
     * Locations to be searched
     */
    public List<SearchLocation> locations;

    public List<SearchLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<SearchLocation> locations) {
        this.locations = locations;
    }
}
