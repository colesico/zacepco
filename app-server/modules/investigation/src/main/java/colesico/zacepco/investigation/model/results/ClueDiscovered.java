package colesico.zacepco.investigation.model.results;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;

public sealed class ClueDiscovered
        extends InspectionResult
        permits ClueFocusDiscovered {

    public final ClueSpec clue;

    /**
     * Clues remaining to discover
     */
    public final Integer cluesRemaining;

    /**
     * Points awarded to the detective
     */
    public Integer awardedPoints;

    public Integer budgetSpending;

    protected ClueDiscovered(ClueSpec clue, Integer cluesRemaining) {
        this.clue = clue;
        this.cluesRemaining = cluesRemaining;
    }

    public static ClueDiscovered of(ClueSpec clue, Integer cluesRemaining) {
        return new ClueDiscovered(clue, cluesRemaining);
    }
}
