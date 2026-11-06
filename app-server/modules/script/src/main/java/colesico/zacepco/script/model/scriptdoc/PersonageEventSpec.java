package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;


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
    @YamlComment(text = "Clue linked to this personage during the tick")
    public ClueSpec.Id clue;

}
