package colesico.zacepco.script.model.setting;

import colesico.zacepco.script.yaml.YamlComment;

import java.util.List;

/**
 * Game character
 */
public class Personage extends Entity<PersonageId> {

    /**
     * Hidden personage or not
     */
    @YamlComment(text = "Hidden personage or not (default - false)")
    public Boolean hidden;

    @YamlComment(text = "Initially revealed clues for this personage")
    public List<ClueId> revealedClues;

    /**
     * Personage-entity interaction for each time tick.
     */
    @YamlComment(text = {
            "Personage event log for each time tick",
            "Each element of the array corresponds to a time tick, starting from 1.",
            "Used to verify the investigation's findings"
    })
    public List<PersonageEvent> log;

    public boolean hidden() {
        return hidden != null ? hidden : false;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public List<PersonageEvent> getLog() {
        return log;
    }

    public void setLog(List<PersonageEvent> log) {
        this.log = log;
    }

    public List<ClueId> getRevealedClues() {
        return revealedClues;
    }

    public void setRevealedClues(List<ClueId> revealedClues) {
        this.revealedClues = revealedClues;
    }
}
