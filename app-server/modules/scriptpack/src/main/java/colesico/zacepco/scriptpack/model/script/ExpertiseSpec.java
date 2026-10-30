package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;

import java.util.ArrayList;
import java.util.List;

/**
 * Clue expertise specification
 */
public final class ExpertiseSpec extends InvestigationActionSpec {

    /**
     * Clues to be examined
     */
    @YamlComment(text = "Examined clues")
    public List<FocusedClueSpec> clues = new ArrayList<>();

}
