package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;


/**
 * Personage event.
 * Contains information about interactions with other entities at given time tick.
 * Used to verify the investigation's findings.
 */
public final class PersonageEventSpec {

    /**
     * The location the character was inside.
     */
    @YamlComment(text = {
            "Personage destination location for tick",
            "Initial character location not specified - it refers to the previous tick"
    })
    public LocationSpec.Id location;

    /**
     * The clue the character interacted with
     */
    @YamlComment(text = {
            "Clue resulting from a personage's action",
            "Action examples: picking up or leaving an item, spotting someone somewhere"
    })
    public ClueSpec.Id clue;

}
