package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Clue expertise specification
 */
public final class ExpertiseSpec extends InvestigationActionSpec {

    @YamlComment(text = "Examined clues")
    public Map<ClueSpec.Id, FocusedClueSpec> clues = new LinkedHashMap<>();

    public ExpertiseSpec() {
        this.cost = 1;
        this.points = 3;
        this.focusLimit = 3;
    }
}
