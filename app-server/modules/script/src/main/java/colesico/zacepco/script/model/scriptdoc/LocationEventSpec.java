package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlCompact;
import colesico.zacepco.script.yaml.YamlIgnore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Location event.
 * Contains information about interactions with other entities at given time tick.
 * Used to verify the investigation's findings.
 */
public final class LocationEventSpec {

    @YamlIgnore
    public TickSpec.Id tick;

    @YamlComment(text = "Personages whose destination is this location for the given tick")
    @YamlCompact
    public Set<PersonageSpec.Id> personages = new HashSet<>();

    /**
     * The clues the location interacted with
     */
    @YamlComment(text = "Clues linked to this location during the tick")
    @YamlCompact
    public Set<ClueSpec.Id> clues = new HashSet<>();

}
