package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Investigation specification
 */
public final class InvestigationSpec {

    /**
     * Investigation budget (resource)
     */
    @YamlComment(text = {
            "Investigation budget in resource units",
            "Default: 2147483647"
    })
    public Integer budget = Integer.MAX_VALUE;

    @YamlComment(text = {
            "Planned crime solving strategy. ",
            "Example for location search: ",
            "   - inspection: SEARCH",
            "   target: L2",
            "   focus: [T4,C2]",
            "   result: C5"
    })
    public List<StrategyItemSpec> strategy = new ArrayList<>();

    /**
     * Location search
     */
    @YamlComment(text = {
            "",
            "Search specification",
            "for location or personage",
            ""
    })
    public SearchSpec search = new SearchSpec();

    /**
     * Personage interrogation
     */
    @YamlComment(text = {
            "",
            "Personage interrogation specification",
            ""
    })
    public InterrogationSpec interrogation = new InterrogationSpec();


    @YamlComment(text = {
            "",
            "Criminal examination specification",
            ""
    })
    public ExaminationSpec examination = new ExaminationSpec();

    @YamlComment(text = {
            "",
            "Detectives dictionary",
            "A subset of which can participate in the investigation",
            ""
    })
    public Map<DetectiveSpec.Id, DetectiveSpec> detectives = new LinkedHashMap<>();

}
