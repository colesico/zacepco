package colesico.zacepco.script.model.investigation;

import colesico.zacepco.script.yaml.YamlComment;

import java.util.List;

/**
 * Search specification
 */
public final class Search extends InvestigationAction {

    /**
     * Locations to be searched
     */
    @YamlComment(text = "Locations to be searched")
    public List<LocationSearch> locations;

    public List<LocationSearch> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationSearch> locations) {
        this.locations = locations;
    }
}
