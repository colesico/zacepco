package colesico.zacepco.respack.model.script;

import colesico.zacepco.respack.yaml.YamlComment;

import java.util.List;

/**
 * General answers
 */
public class Answers {

    @YamlComment(text = "Full crime fabula")
    public String fabula;

    @YamlComment(text = {
            "Planned crime solving strategy. Example: ",
            " - Search library L2 -> C1",
            " - Interrogate gardener P3 -> C4"
    })
    public List<String> strategy;

    public String getFabula() {
        return fabula;
    }

    public void setFabula(String fabula) {
        this.fabula = fabula;
    }

    public List<String> getStrategy() {
        return strategy;
    }

    public void setStrategy(List<String> strategy) {
        this.strategy = strategy;
    }
}
