package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;

import java.util.List;

/**
 * Search specification
 */
public final class SearchSpec extends InvestigationActionSpec {

    /**
     * Locations to be searched
     */
    @YamlComment(text = "Locations to be searched")
    public List<LocationSearchSpec> locations;

    public List<LocationSearchSpec> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationSearchSpec> locations) {
        this.locations = locations;
    }
}
