package colesico.zacepco.investigation.model.results;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;

abstract public class ClueResult extends InspectionResult{

    public final ClueSpec clue;

    /**
     * Points awarded to the detective
     */
    public Integer awardedPoints;

    public ClueResult(ClueSpec clue) {
        this.clue = clue;
    }
}
