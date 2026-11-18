package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlCompact;
import colesico.zacepco.script.yaml.YamlIgnore;

import java.util.*;

public final class FocusedClueSpec {

    /**
     * Clue to be uncovered
     */
    @YamlIgnore
    public ClueSpec.Id clue;

    /**
     * Entities on which investigation action is focused
     */
    @YamlComment(text = "Entities on which investigation action is focused")
    @YamlCompact
    public Set<TId> focus = new LinkedHashSet<>();

    /**
     * Clue uncover threshold
     */
    @YamlComment(text = {
            "Clue uncover threshold",
            "Default: 0.0"
    })
    public Double threshold = 0.0;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FocusedClueSpec that)) return false;
        return Objects.equals(clue, that.clue);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(clue);
    }
}
