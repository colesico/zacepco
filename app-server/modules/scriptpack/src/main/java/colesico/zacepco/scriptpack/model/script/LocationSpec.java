package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;
import colesico.zacepco.scriptpack.yaml.YamlCompact;

import java.util.ArrayList;
import java.util.List;

/**
 * Scene location
 */
public final class LocationSpec extends EntitySpec<LocationSpec.Id> {

    /**
     * Location position on scene grid
     */
    @YamlComment(text = {
            "Location position on scene grid",
            "(1,1) - left top scene corner"
    })
    public LocationPosSpec pos = new LocationPosSpec();

    /**
     * Hidden location or not
     */
    @YamlComment(text = {
            "Initially hidden location or not",
            "Default: false"
    })
    public Boolean hidden = false;

    /**
     * Initially revealed clues for this location
     */
    @YamlComment(text = "Initially exposed clues for this location")
    @YamlCompact
    public List<ClueSpec.Id> exposedClues = new ArrayList<>();

    /**
     * Personage-entity interaction for each time tick.
     */
    @YamlComment(text = {
            "Location event log for each time tick",
            "Each element of the array corresponds to a time tick, starting from 1",
            "Used to verify the investigation's findings as answers"
    })
    public List<LocationEventSpec> log = new ArrayList<>();

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
