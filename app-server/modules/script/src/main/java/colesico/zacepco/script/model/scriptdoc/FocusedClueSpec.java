package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlCompact;
import colesico.zacepco.script.yaml.YamlIgnore;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

}
