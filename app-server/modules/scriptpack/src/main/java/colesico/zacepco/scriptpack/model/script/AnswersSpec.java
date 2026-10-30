package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;

import java.util.ArrayList;
import java.util.List;

/**
 * General answers
 */
public final class AnswersSpec {

    @YamlComment(text = "Full crime fabula")
    public String fabula;

    @YamlComment(text = {
            "Planned crime solving strategy. Example: ",
            " - Search library L2 -> C1",
            " - Interrogate gardener P3 -> C4"
    })
    public List<String> strategy = new ArrayList<>();

}
