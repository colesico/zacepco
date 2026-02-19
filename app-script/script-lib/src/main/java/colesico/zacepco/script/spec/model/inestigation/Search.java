package colesico.zacepco.script.spec.model.inestigation;

import java.util.List;

/**
 * Search specification
 */
public final class Search extends InvestigationAction {

    public List<SearchLocation> locations;

    public List<SearchLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<SearchLocation> locations) {
        this.locations = locations;
    }
}
