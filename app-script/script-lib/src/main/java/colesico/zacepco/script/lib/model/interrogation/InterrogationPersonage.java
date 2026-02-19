package colesico.zacepco.script.lib.model.interrogation;

import colesico.zacepco.script.lib.model.entity.PersonageId;

import java.util.List;

/**
 * Personage interrogation
 */
public class InterrogationPersonage {

    /**
     * Personage Id
     */
    public PersonageId presonage;

    public List<InterrogationClue> clues;

    public PersonageId getPresonage() {
        return presonage;
    }

    public void setPresonage(PersonageId presonage) {
        this.presonage = presonage;
    }

    public List<InterrogationClue> getClues() {
        return clues;
    }

    public void setClues(List<InterrogationClue> clues) {
        this.clues = clues;
    }
}
