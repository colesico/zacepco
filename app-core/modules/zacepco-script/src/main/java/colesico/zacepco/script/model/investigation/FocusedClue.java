package colesico.zacepco.script.model.investigation;

import colesico.zacepco.script.model.setting.ClueId;
import colesico.zacepco.script.model.setting.EntityId;

import java.util.List;

public class FocusedClue {
    /**
     * Entities on which search is focused
     */
    public List<EntityId> focus;

    /**
     * Clue discovery threshold
     */
    public Double threshold;

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

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    public ClueId getClue() {
        return clue;
    }

    public void setClue(ClueId clue) {
        this.clue = clue;
    }
}
