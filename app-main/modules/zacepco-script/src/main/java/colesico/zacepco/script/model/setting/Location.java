package colesico.zacepco.script.model.setting;

import colesico.zacepco.script.yaml.YamlComment;

import java.util.List;

/**
 * Scene location
 */
public class Location extends Entity<LocationId> {

    /**
     * Location position on scene grid
     */
    @YamlComment(text = "Location position on scene grid")
    public LocationPos pos;

    /**
     * Initially revealed clues for this location
     */
    @YamlComment(text = "Initially revealed clues for this location")
    public List<ClueId> revealedClues;

    /**
     * Hidden location or not
     */
    @YamlComment(text = "Hidden location or not  (default - false)")
    public Boolean hidden;

    /**
     * Personage-entity interaction for each time tick.
     */
    @YamlComment(text = {
            "Location event log for each time tick",
            "Each element of the array corresponds to a time tick, starting from 1.",
            "Used to verify the investigation's findings"
    })
    public List<LocationEvent> log;

    public boolean hidden() {
        return hidden != null ? hidden : false;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public LocationPos getPos() {
        return pos;
    }

    public void setPos(LocationPos pos) {
        this.pos = pos;
    }

    public List<ClueId> getRevealedClues() {
        return revealedClues;
    }

    public void setRevealedClues(List<ClueId> revealedClues) {
        this.revealedClues = revealedClues;
    }

    public List<LocationEvent> getLog() {
        return log;
    }

    public void setLog(List<LocationEvent> log) {
        this.log = log;
    }
}
