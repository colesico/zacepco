package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;
import colesico.zacepco.scriptpack.yaml.YamlCompact;

import java.util.List;

/**
 * Scene location
 */
public class LocationSpec extends EntitySpec<LocationSpec.Id> {

    /**
     * Location position on scene grid
     */
    @YamlComment(text = {
            "Location position on scene grid",
            "(1,1) - left top scene corner"
    })
    public LocationPosSpec pos;

    /**
     * Hidden location or not
     */
    @YamlComment(text = "Initially hidden location or not  (default - false)")
    public Boolean hidden;

    /**
     * Initially revealed clues for this location
     */
    @YamlComment(text = "Initially exposed clues for this location")
    @YamlCompact
    public List<ClueSpec.Id> exposedClues;

    /**
     * Personage-entity interaction for each time tick.
     */
    @YamlComment(text = {
            "Location event log for each time tick",
            "Each element of the array corresponds to a time tick, starting from 1",
            "Used to verify the investigation's findings as answers"
    })
    public List<LocationEventSpec> log;

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

    public LocationPosSpec getPos() {
        return pos;
    }

    public void setPos(LocationPosSpec pos) {
        this.pos = pos;
    }

    public List<ClueSpec.Id> getExposedClues() {
        return exposedClues;
    }

    public void setExposedClues(List<ClueSpec.Id> exposedClues) {
        this.exposedClues = exposedClues;
    }

    public List<LocationEventSpec> getLog() {
        return log;
    }

    public void setLog(List<LocationEventSpec> log) {
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
