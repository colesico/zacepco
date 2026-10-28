package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;
import colesico.zacepco.scriptpack.yaml.YamlCompact;

import java.util.Set;

/**
 * Unidirectional connection between two locations
 */
public class Way {

    /**
     * Related locations
     */
    @YamlComment(text = "IDs of the two connected locations")
    @YamlCompact
    public Set<Location.Id> locations;

    @YamlComment(text = "Restrictions or characteristics of the way")
    public String description;

    /**
     * Is hidden passage
     */
    @YamlComment(text = "Is hidden transition or not (default - false)")
    public Boolean hidden;

    public boolean isHidden() {
        return hidden != null ? hidden : false;
    }

    public void setLocations(Set<Location.Id> locations) {
        if (locations == null || locations.size() != 2) {
            throw new IllegalStateException("Invalid locations ID's. Specify two ID");
        }
        this.locations = locations;
    }

    public Set<Location.Id> getLocations() {
        return locations;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
