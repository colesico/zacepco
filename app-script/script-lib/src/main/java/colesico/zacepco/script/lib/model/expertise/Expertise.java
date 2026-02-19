package colesico.zacepco.script.lib.model.expertise;

import colesico.zacepco.script.lib.model.common.FocusedClue;
import colesico.zacepco.script.lib.model.common.InvestigationAction;

import java.util.List;

/**
 * Clue expertise specification
 */
public class Expertise extends InvestigationAction {

    /**
     * Clues to be examined
     */
    public List<FocusedClue> clues;

    public List<FocusedClue> getClues() {
        return clues;
    }

    public void setClues(List<FocusedClue> clues) {
        this.clues = clues;
    }
}
