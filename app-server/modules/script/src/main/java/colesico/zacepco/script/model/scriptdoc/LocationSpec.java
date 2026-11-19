package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlCompact;

import java.util.*;

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
    @YamlCompact
    public LocationPosSpec pos = new LocationPosSpec();

    /**
     * Hidden location or not
     */
    @YamlComment(text = {
            "Initially hidden location or not",
            "Default: false"
    })
    public Boolean hidden = false;

    @YamlComment(text = "Location related clues")
    @YamlCompact
    public Set<ClueSpec.Id> clues = new LinkedHashSet<>();

    @YamlComment(text = {
            "Location log",
            "Each element corresponds to a time tick",
            "Used to verify the investigation's findings (answers)"
    })
    public Map<TickSpec.Id, LocationEventSpec> log = new LinkedHashMap<>();

    public static final class Id extends TId {

        public Id(String key) {
            super(key);
        }

        @Override
        public Type type() {
            return Type.LOCATION;
        }

    }
}
