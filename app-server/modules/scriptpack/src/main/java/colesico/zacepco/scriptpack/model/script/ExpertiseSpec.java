package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;

import java.util.List;

/**
 * Clue expertise specification
 */
public class ExpertiseSpec extends InvestigationActionSpec {

    /**
     * Clues to be examined
     */
    @YamlComment(text = "Examined clues")
    public List<FocusedClueSpec> clues;

    public List<FocusedClueSpec> getClues() {
        return clues;
    }

    public void setClues(List<FocusedClueSpec> clues) {
        this.clues = clues;
    }
}
