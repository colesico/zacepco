package colesico.zacepco.script.lib.model;

import java.util.List;

/**
 * Clue expertise specification
 */
public class ExpertiseSpec {

    /**
     * Expertise cost  (resources amount)
     */
    public ExpertiseCost cost;

    /**
     * Clues to be examined
     */
    public List<ExpertiseClue> clues;

    public ExpertiseCost getCost() {
        return cost;
    }

    public void setCost(ExpertiseCost cost) {
        this.cost = cost;
    }
}
