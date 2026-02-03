package colesico.zacepco.script.lib.model;

import java.util.List;

/**
 * Clue expertise specification
 */
public class ExpertiseSpec {

    /**
     * Initial expertise cost  (resources amount)
     */
    public Integer cost;

    /**
     * Subsequent expertises cost
     */
    public Integer costSub;

    /**
     * Clues to be examined
     */
    public List<ExpertiseClue> clues;
}
