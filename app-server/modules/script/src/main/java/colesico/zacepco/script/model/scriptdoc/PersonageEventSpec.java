package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlCompact;
import colesico.zacepco.script.yaml.YamlIgnore;

import java.util.Set;


/**
 * Personage event.
 * Contains information about interactions with other entities at given time tick.
 * Used to verify the investigation's findings.
 */
public final class PersonageEventSpec {


    @YamlIgnore
    public TickSpec.Id tick;

    /**
     * The location the character was inside.
     */
    @YamlComment(text = {
            "Personage destination location for tick",
            "Initial character location not specified - it refers to the previous tick"
    })
    public LocationSpec.Id location;

    @YamlComment(text = "Clues linked to this personage during the tick")
    @YamlCompact
    public Set<ClueSpec.Id> clue;

}
