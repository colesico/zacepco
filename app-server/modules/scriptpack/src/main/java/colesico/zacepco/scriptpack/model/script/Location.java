package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;
import colesico.zacepco.scriptpack.yaml.YamlCompact;

import java.util.List;

/**
 * Scene location
 */
public class Location extends Entity<Location.Id> {

    /**
     * Location position on scene grid
     */
    @YamlComment(text = {
            "Location position on scene grid",
            "(1,1) - left top scene corner"
    })
    public LocationPos pos;

    /**
     * Hidden location or not
     */
    @YamlComment(text = "Hidden location or not  (default - false)")
    public Boolean hidden;

    /**
     * Initially revealed clues for this location
     */
    @YamlComment(text = "Initially revealed clues for this location")
    @YamlCompact
    public List<Clue.Id> revealedClues;

    /**
     * Personage-entity interaction for each time tick.
     */
    @YamlComment(text = {

            "Location event log for each time tick",
            "Each element of the array corresponds to a time tick, starting from 1.",
            "Used to verify the investigation's findings"
    })
    public List<LocationEvent> log;

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

    public LocationPos getPos() {
        return pos;
    }

    public void setPos(LocationPos pos) {
        this.pos = pos;
    }

    public List<Clue.Id> getRevealedClues() {
        return revealedClues;
    }

    public void setRevealedClues(List<Clue.Id> revealedClues) {
        this.revealedClues = revealedClues;
    }

    public List<LocationEvent> getLog() {
        return log;
    }

    public void setLog(List<LocationEvent> log) {
        this.log = log;
    }

    public static final class Id extends UID {

        public Id(String key) {
            super(key);
        }

        @Override
        public Type type() {
            return Type.LOCATION;
        }

    }
}
