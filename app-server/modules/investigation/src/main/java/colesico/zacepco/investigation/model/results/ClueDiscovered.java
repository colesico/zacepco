package colesico.zacepco.investigation.model.results;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;

public sealed class ClueDiscovered
        extends InspectionResult
        permits ClueFocusDiscovered {

    public final ClueSpec clue;

    /**
     * Points awarded to the detective
     */
    public Integer awardedPoints;

    public Integer budgetSpending;

    protected ClueDiscovered(ClueSpec clue) {
        this.clue = clue;
    }

    public static ClueDiscovered of(ClueSpec clue) {
        return new ClueDiscovered(clue);
    }
}
