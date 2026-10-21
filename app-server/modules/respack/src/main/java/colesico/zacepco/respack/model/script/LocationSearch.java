package colesico.zacepco.respack.model.script;

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
    public Location.Id location;

    /**
     * Clues definitions
     */
    public List<FocusedClue> clues;

    public Location.Id getLocation() {
        return location;
    }

    public void setLocation(Location.Id location) {
        this.location = location;
    }

    public List<FocusedClue> getClues() {
        return clues;
    }

    public void setClues(List<FocusedClue> clues) {
        this.clues = clues;
    }
}
