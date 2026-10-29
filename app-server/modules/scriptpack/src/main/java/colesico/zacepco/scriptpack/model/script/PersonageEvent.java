package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;


/**
 * Personage event.
 * Contains information about interactions with other entities at given time tick.
 * Used to verify the investigation's findings.
 */
public class PersonageEvent {

    /**
     * The location the character was inside.
     */
    @YamlComment(text = {
            "Personage destination location for tick",
            "Initial character location not specified - it refers to the previous tick"
    })
    public Location.Id location;

    /**
     * The clue the character interacted with
     */
    @YamlComment(text = {
            "Clue resulting from a personage's action",
            "Action examples: picking up or leaving an item, spotting someone somewhere"
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
