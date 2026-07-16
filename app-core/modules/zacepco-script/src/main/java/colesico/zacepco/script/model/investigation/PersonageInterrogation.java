package colesico.zacepco.script.model.investigation;

import colesico.zacepco.script.model.setting.PersonageId;
import colesico.zacepco.script.yaml.YamlComment;

import java.util.List;

/**
 * Personage interrogation
 */
public class PersonageInterrogation {

    /**
     * Personage Id
     */
    @YamlComment(text = "Personage ID")
    public PersonageId personage;

    @YamlComment(text = "Personage clues")
    public List<FocusedClue> clues;

    public PersonageId getPersonage() {
        return personage;
    }

    public void setPersonage(PersonageId personage) {
        this.personage = personage;
    }

    public List<FocusedClue> getClues() {
        return clues;
    }

    public void setClues(List<FocusedClue> clues) {
        this.clues = clues;
    }
}
