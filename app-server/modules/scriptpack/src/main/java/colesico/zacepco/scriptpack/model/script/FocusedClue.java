package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;
import colesico.zacepco.scriptpack.yaml.YamlCompact;

import java.util.List;

public class FocusedClue {
    /**
     * Entities on which investigation action is focused
     */
    @YamlComment(text = "Entities on which investigation action is focused")
    @YamlCompact
    public List<Entity.UID> focus;

    /**
     * Clue discovery threshold
     */
    @YamlComment(text = "Clue discovery threshold")
    public Double threshold;

    /**
     * Target clue
     */
    @YamlComment(text = "The target clue to be uncovered")
    public Clue.Id clue;

    public List<Entity.UID> getFocus() {
        return focus;
    }

    public void setFocus(List<Entity.UID> focus) {
        this.focus = focus;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    public Clue.Id getClue() {
        return clue;
    }

    public void setClue(Clue.Id clue) {
        this.clue = clue;
    }
}
