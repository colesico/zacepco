package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;
import colesico.zacepco.scriptpack.yaml.YamlCompact;

import java.util.HashSet;
import java.util.Set;

/**
 * Unidirectional way between two locations
 */
public class WaySpec {

    /**
     * Related locations
     */
    @YamlComment(text = "IDs of the two connected locations")
    @YamlCompact
    public Set<LocationSpec.Id> locations = new HashSet<>();

    @YamlComment(text = {
            "Restrictions or characteristics of the way",
            "Used by detectives to estimate the travel time and whether a personage can move along it"
    })
    public String description;

    /**
     * Is hidden passage
     */
    @YamlComment(text = {
            "Is initially hidden way or not",
            "Default: false"
    })
    public Boolean hidden = false;

    public void setLocations(Set<LocationSpec.Id> locations) {
        if (locations == null || locations.size() != 2) {
            throw new IllegalStateException("Invalid locations ID's. Specify two ID");
        }
        this.locations = locations;
    }

}
