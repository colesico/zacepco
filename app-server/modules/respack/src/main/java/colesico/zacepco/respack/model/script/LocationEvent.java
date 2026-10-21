package colesico.zacepco.respack.model.script;

import colesico.zacepco.respack.yaml.YamlComment;

import java.util.List;

/**
 * Location event.
 * Contains information about interactions with other entities at given time tick.
 * Used to verify the investigation's findings.
 */
public class LocationEvent {

    @YamlComment(text = "Personages that was in this location at given time tick")
    public List<Personage.Id> personages;

    /**
     * The clues the location interacted with
     */
    @YamlComment(text = "Clue associated with the location")
    public List<Clue.Id> clues;

    public List<Personage.Id> getPersonages() {
        return personages;
    }

    public void setPersonages(List<Personage.Id> personages) {
        this.personages = personages;
    }

    public List<Clue.Id> getClues() {
        return clues;
    }

    public void setClues(List<Clue.Id> clues) {
        this.clues = clues;
    }
}
