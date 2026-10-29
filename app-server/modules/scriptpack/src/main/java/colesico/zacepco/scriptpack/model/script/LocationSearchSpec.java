package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;

import java.util.List;

/**
 * Location search
 */
public class LocationSearchSpec {

    /**
     * Location to be search
     */
    @YamlComment(text = "Location to be search")
    public LocationSpec.Id location;

    /**
     * Clues definitions
     */
    public List<FocusedClueSpec> clues;

    public LocationSpec.Id getLocation() {
        return location;
    }

    public void setLocation(LocationSpec.Id location) {
        this.location = location;
    }

    public List<FocusedClueSpec> getClues() {
        return clues;
    }

    public void setClues(List<FocusedClueSpec> clues) {
        this.clues = clues;
    }
}
