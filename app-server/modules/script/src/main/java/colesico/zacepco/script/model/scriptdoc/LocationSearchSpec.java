package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlIgnore;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Location search
 */
public final class LocationSearchSpec {

    /**
     * Location to be search
     */
    @YamlIgnore
    public LocationSpec.Id location;

    @YamlComment(text = "Target location clues to be uncovered")
    public Map<ClueSpec.Id, FocusedClueSpec> clues = new LinkedHashMap<>();

}
