package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;

import java.util.ArrayList;
import java.util.List;

/**
 * General answers
 */
public final class AnswersSpec {

    @YamlComment(text = "Full crime fabula")
    public String fabula;

    @YamlComment(text = {
            "Planned crime solving strategy. ",
            " Specify field: ",
            " 'location' for search; ",
            " 'personage' for interrogation; ",
            " nothing for expertise",
            "",
            "Example for location search: ",
            "   - location: L2",
            "   focus: [T4,C2]",
            "   result: C5"
    })
    public List<StrategyItemSpec> strategy = new ArrayList<>();

}
