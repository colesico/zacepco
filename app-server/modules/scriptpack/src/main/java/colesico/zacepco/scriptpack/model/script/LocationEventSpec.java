package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;
import colesico.zacepco.scriptpack.yaml.YamlCompact;

import java.util.List;

/**
 * Location event.
 * Contains information about interactions with other entities at given time tick.
 * Used to verify the investigation's findings.
 */
public class LocationEventSpec {

    @YamlComment(text = "Personages whose destination is this location for the given tick")
    @YamlCompact
    public List<PersonageSpec.Id> personages;

    /**
     * The clues the location interacted with
     */
    @YamlComment(text = "Clues linked to this location during the tick")
    @YamlCompact
    public List<ClueSpec.Id> clues;

    public List<PersonageSpec.Id> getPersonages() {
        return personages;
    }

    public void setPersonages(List<PersonageSpec.Id> personages) {
        this.personages = personages;
    }

    public List<ClueSpec.Id> getClues() {
        return clues;
    }

    public void setClues(List<ClueSpec.Id> clues) {
        this.clues = clues;
    }
}
