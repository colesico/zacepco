package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;

import java.util.ArrayList;
import java.util.List;

/**
 * Search specification
 */
public final class SearchSpec extends InvestigationActionSpec {

    /**
     * Locations to be searched
     */
    @YamlComment(text = "Locations to be searched")
    public List<LocationSearchSpec> locations = new ArrayList<>();

}
