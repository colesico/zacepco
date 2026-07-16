package colesico.zacepco.script.model.setting;

import colesico.zacepco.script.yaml.YamlComment;

import java.util.List;

/**
 * Location event.
 * Contains information about interactions with other entities at given time tick.
 * Used to verify the investigation's findings.
 */
public class LocationEvent {

    @YamlComment(text = "Personages that was in this location at given time tick")
    public List<PersonageId> personages;

    /**
     * The clues the location interacted with
     */
    @YamlComment(text = "Clue the location interacted with")
    public List<ClueId> clues;

    public List<PersonageId> getPersonages() {
        return personages;
    }

    public void setPersonages(List<PersonageId> personages) {
        this.personages = personages;
    }

    public List<ClueId> getClues() {
        return clues;
    }

    public void setClues(List<ClueId> clues) {
        this.clues = clues;
    }
}
