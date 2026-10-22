package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;
import colesico.zacepco.scriptpack.yaml.YamlCompact;

import java.util.List;

/**
 * Game character
 */
public class Personage extends Entity<Personage.Id> {

    /**
     * Hidden personage or not
     */
    @YamlComment(text = "Hidden personage or not (default - false)")
    public Boolean hidden;

    @YamlComment(text = "Initially revealed clues for this personage")
    @YamlCompact
    public List<Clue.Id> revealedClues;

    /**
     * Personage-entity interaction for each time tick.
     */
    @YamlComment(text = {

            "Personage event log for each time tick",
            "Each element of the array corresponds to a time tick, starting from 1.",
            "Used to verify the investigation's findings (answers)"
    })
    public List<PersonageEvent> log;

    public boolean isHidden() {
        return hidden != null ? hidden : false;
    }

    @Override
    public void setId(Id id) {
        super.setId(id);
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

    public List<Clue.Id> getRevealedClues() {
        return revealedClues;
    }

    public void setRevealedClues(List<Clue.Id> revealedClues) {
        this.revealedClues = revealedClues;
    }

    public static final class Id extends UID {

        @Override
        public Type type() {
            return Type.PERSONAGE;
        }

        public Id(String value) {
            super(value);
        }
    }
}
