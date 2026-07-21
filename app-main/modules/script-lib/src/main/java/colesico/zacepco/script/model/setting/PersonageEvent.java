package colesico.zacepco.script.model.setting;

import colesico.zacepco.script.yaml.YamlComment;


/**
 * Personage event.
 * Contains information about interactions with other entities at given time tick.
 * Used to verify the investigation's findings.
 */
public class PersonageEvent {

    /**
     * The location the character was inside.
     */
    @YamlComment(text = "Location the character was inside")
    public LocationId location;

    /**
     * The clue the character interacted with
     */
    @YamlComment(text = {
            "Clue the character interacted with.",
            "Reflects the change of interaction state - dropping, picking up, passing, ..."
    })
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
