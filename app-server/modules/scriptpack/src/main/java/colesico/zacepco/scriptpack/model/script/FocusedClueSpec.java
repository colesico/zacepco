package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;
import colesico.zacepco.scriptpack.yaml.YamlCompact;

import java.util.ArrayList;
import java.util.List;

public final class FocusedClueSpec {

    /**
     * Entities on which investigation action is focused
     */
    @YamlComment(text = "Entities on which investigation action is focused")
    @YamlCompact
    public List<EntitySpec.UID> focus = new ArrayList<>();

    /**
     * Clue uncover threshold
     */
    @YamlComment(text = {
            "Clue uncover threshold",
            "Default: 0.0"
    })
    public Double threshold = 0.0;

    /**
     * Target clue
     */
    @YamlComment(text = "The target clue to be uncovered")
    public ClueSpec.Id clue;


}
