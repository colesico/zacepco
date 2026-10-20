package colesico.zacepco.respack.model.investigation;

import colesico.zacepco.respack.model.setting.LocationId;
import colesico.zacepco.respack.yaml.YamlComment;

import java.util.List;

/**
 * Location search
 */
public class LocationSearch {

    /**
     * Location to be search
     */
    @YamlComment(text = "Location to be search")
    public LocationId location;

    /**
     * Clues definitions
     */
    public List<FocusedClue> clues;

    public LocationId getLocation() {
        return location;
    }

    public void setLocation(LocationId location) {
        this.location = location;
    }

    public List<FocusedClue> getClues() {
        return clues;
    }

    public void setClues(List<FocusedClue> clues) {
        this.clues = clues;
    }
}
