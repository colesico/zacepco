package colesico.zacepco.script.lib.model.search;

import colesico.zacepco.script.lib.model.common.FocusedClue;
import colesico.zacepco.script.lib.model.entity.LocationId;

import java.util.List;

/**
 * Location search
 */
public class SearchLocation {

    /**
     * Location to be search
     */
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
