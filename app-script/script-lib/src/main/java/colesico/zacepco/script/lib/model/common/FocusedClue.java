package colesico.zacepco.script.lib.model.common;

import colesico.zacepco.script.lib.model.entity.ClueId;
import colesico.zacepco.script.lib.model.entity.EntityId;

import java.util.List;

public class FocusedClue {
    /**
     * Entities on which search is focused
     */
    public List<EntityId> focus;

    /**
     * Clue discovery threshold
     */
    public Integer threshold;

    /**
     * Target clue
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
