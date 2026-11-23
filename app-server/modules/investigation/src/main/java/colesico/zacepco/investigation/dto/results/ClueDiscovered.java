package colesico.zacepco.investigation.dto.results;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;

public sealed class ClueDiscovered
        extends InspectionResult
        implements SearchResult
        permits ClueFocusDiscovered {

    public ClueSpec clue;

    /**
     * Clues remaining to discover
     */
    public Integer cluesRemaining;

    /**
     * Points awarded to the detective
     */
    public Integer awardedPoints;

    public ClueDiscovered(ClueSpec clue, Integer cluesRemaining, Integer awardedPoints) {
        super("CLUE_DISCOVERED");
        this.clue = clue;
        this.cluesRemaining = cluesRemaining;
        this.awardedPoints = awardedPoints;
    }

    protected ClueDiscovered(String code, ClueSpec clue, Integer cluesRemaining, Integer awardedPoints) {
        super(code);
        this.clue = clue;
        this.cluesRemaining = cluesRemaining;
        this.awardedPoints = awardedPoints;
    }
}
