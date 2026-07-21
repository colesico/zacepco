package colesico.zacepco.script.lib.model.script;

import colesico.zacepco.script.lib.yaml.YamlComment;

import java.util.List;

/**
 * General answers
 */
public class Answers {

    @YamlComment(text = "Full crime fabula")
    public String fabula;

    @YamlComment(text = "Planned crime solving strategy")
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
