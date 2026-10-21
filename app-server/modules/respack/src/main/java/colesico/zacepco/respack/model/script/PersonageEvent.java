package colesico.zacepco.respack.model.script;

import colesico.zacepco.respack.yaml.YamlComment;


/**
 * Personage event.
 * Contains information about interactions with other entities at given time tick.
 * Used to verify the investigation's findings.
 */
public class PersonageEvent {

    /**
     * The location the character was inside.
     */
    @YamlComment(text = "Personage destination location")
    public Location.Id location;

    /**
     * The clue the character interacted with
     */
    @YamlComment(text = {
            "Clue the character interacted with.",
            "Reflects the change of interaction state - gave, got,  noticed  ..."
    })
    public Clue.Id clue;

    public Location.Id getLocation() {
        return location;
    }

    public void setLocation(Location.Id location) {
        this.location = location;
    }

    public Clue.Id getClue() {
        return clue;
    }

    public void setClue(Clue.Id clue) {
        this.clue = clue;
    }
}
