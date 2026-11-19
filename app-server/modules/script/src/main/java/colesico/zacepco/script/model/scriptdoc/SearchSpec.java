package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Search specification
 */
public final class SearchSpec extends InvestigationActionSpec {

    @YamlComment(text = "Location clues to be uncovered")
    public Map<ClueSpec.Id, FocusedClueSpec> clues = new LinkedHashMap<>();

    public SearchSpec() {
        this.cost = 3;
        this.points = 1;
        this.focusLimit = 1;
    }
}
