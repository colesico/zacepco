package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;

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

    public SearchSpec() {
        this.cost = 3;
        this.points = 1;
        this.focusLimit = 1;
    }
}
