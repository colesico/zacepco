package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlCompact;

import java.util.ArrayList;
import java.util.List;

/**
 * Scene location
 */
@YamlComment(text = {
        "Unique location ID",
        "Format: L[A-Z0-9]{1,3}"
}, field = "id")
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


    public static final class Id extends TypedId {

        public Id(String key) {
            super(key);
        }

        @Override
        public Type type() {
            return Type.LOCATION;
        }

    }
}
