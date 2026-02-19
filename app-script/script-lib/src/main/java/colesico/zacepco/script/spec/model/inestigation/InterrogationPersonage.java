package colesico.zacepco.script.spec.model.inestigation;

import colesico.zacepco.script.spec.model.entity.PersonageId;

import java.util.List;

/**
 * Personage interrogation
 */
public class InterrogationPersonage {

    /**
     * Personage Id
     */
    public PersonageId personage;

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
