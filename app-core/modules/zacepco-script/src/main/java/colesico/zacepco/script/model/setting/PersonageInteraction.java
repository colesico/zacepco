package colesico.zacepco.script.model.setting;

import java.util.List;

/**
 * Personage-entity interaction log.
 * Contains IDs of entities interacted with by personage per tick.
 * Used to verify the investigation's findings.
 */
public class PersonageInteraction {
    /**
     * The location the character was inside.
     */
    public LocationId location;

    /**
     * The clue the character interacted with
     */
    public ClueId clue;

    public LocationId getLocation() {
        return location;
    }

    public void setLocation(LocationId location) {
        this.location = location;
    }

    public ClueId getClue() {
        return clue;
    }

    public void setClue(ClueId clue) {
        this.clue = clue;
    }
}
