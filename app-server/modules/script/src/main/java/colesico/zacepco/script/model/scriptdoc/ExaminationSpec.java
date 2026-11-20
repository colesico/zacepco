package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Examination specification
 */
public final class ExaminationSpec extends InvestigationActionSpec {

    @YamlComment(text = "Examination clues spec")
    public Map<ClueSpec.Id, FocusedClueSpec> clues = new LinkedHashMap<>();

    public ExaminationSpec() {
        this.cost = 1;
        this.points = 3;
        this.focusLimit = 3;
    }
}
