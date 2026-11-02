package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;

import java.util.ArrayList;
import java.util.List;

/**
 * Location search
 */
public final class LocationSearchSpec {

    /**
     * Location to be search
     */
    @YamlComment(text = "Location to be search")
    public LocationSpec.Id location;

    /**
     * Clues definitions
     */
    public List<FocusedClueSpec> clues = new ArrayList<>();

}
