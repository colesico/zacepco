package colesico.zacepco.script.lib.model.interrogation;

import colesico.zacepco.script.lib.model.entity.ClueId;
import colesico.zacepco.script.lib.model.entity.EntityId;

import java.util.List;

public class InterrogationClue {

    /**
     * Entities on which interrogation is focused
     */
    public List<EntityId> focus;

    /**
     * Resistance to the psychological pressure of a detective
     */
    public Integer threshold;

    /**
     * Personage target clue
     */
    public ClueId clue;

    public List<EntityId> getFocus() {
        return focus;
    }

    public void setFocus(List<EntityId> focus) {
        this.focus = focus;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public ClueId getClue() {
        return clue;
    }

    public void setClue(ClueId clue) {
        this.clue = clue;
    }
}
