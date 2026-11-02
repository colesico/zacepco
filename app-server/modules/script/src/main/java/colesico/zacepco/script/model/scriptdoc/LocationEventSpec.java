package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlCompact;

import java.util.ArrayList;
import java.util.List;

/**
 * Location event.
 * Contains information about interactions with other entities at given time tick.
 * Used to verify the investigation's findings.
 */
public final class LocationEventSpec {

    @YamlComment(text = "Personages whose destination is this location for the given tick")
    @YamlCompact
    public List<PersonageSpec.Id> personages = new ArrayList<>();

    /**
     * The clues the location interacted with
     */
    @YamlComment(text = "Clues linked to this location during the tick")
    @YamlCompact
    public List<ClueSpec.Id> clues = new ArrayList<>();

}
