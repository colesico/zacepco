package colesico.zacepco.respack.model.script;

import colesico.zacepco.respack.yaml.YamlComment;

import java.util.List;

public class FocusedClue {
    /**
     * Entities on which investigation action is focused
     */
    @YamlComment(text = "Entities on which investigation action is focused")
    public List<Entity.TypedId> focus;

    /**
     * Clue discovery threshold
     */
    @YamlComment(text = "Clue discovery threshold")
    public Double threshold;

    /**
     * Target clue
     */
    @YamlComment(text = "Target clue")
    public Clue.Id clue;

    public List<Entity.TypedId> getFocus() {
        return focus;
    }

    public void setFocus(List<Entity.TypedId> focus) {
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
