package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;
import colesico.zacepco.scriptpack.yaml.YamlCompact;

import java.util.List;

public class FocusedClueSpec {
    /**
     * Entities on which investigation action is focused
     */
    @YamlComment(text = "Entities on which investigation action is focused")
    @YamlCompact
    public List<EntitySpec.UID> focus;

    /**
     * Clue uncover threshold
     */
    @YamlComment(text = "Clue uncover threshold")
    public Double threshold;

    /**
     * Target clue
     */
    @YamlComment(text = "The target clue to be uncovered")
    public ClueSpec.Id clue;

    public List<EntitySpec.UID> getFocus() {
        return focus;
    }

    public void setFocus(List<EntitySpec.UID> focus) {
        this.focus = focus;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    public ClueSpec.Id getClue() {
        return clue;
    }

    public void setClue(ClueSpec.Id clue) {
        this.clue = clue;
    }
}
