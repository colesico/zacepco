package colesico.zacepco.investigation.dto.action;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;

public sealed class ClueUncover implements LocationSearchResult permits ClueFocusedUncover {

    public ClueSpec clue;

    /**
     * Clues remaining for uncover
     */
    public Integer cluesRemaining;

    /**
     * Points awarded to the detective
     */
    public Integer awardedPoints;

    public ClueUncover(ClueSpec clue, Integer cluesRemaining, Integer awardedPoints) {
        this.clue = clue;
        this.cluesRemaining = cluesRemaining;
        this.awardedPoints = awardedPoints;
    }

    @Override
    public String code() {
        return "CLUE_UNCOVER";
    }
}
