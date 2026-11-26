package colesico.zacepco.investigation.model.results;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;

public sealed class SearchClueDiscovered
        extends InspectionResult
        permits SearchFocusedClueDiscovered {

    public final ClueSpec clue;

    /**
     * Points awarded to the detective
     */
    public Integer awardedPoints;

    protected SearchClueDiscovered(ClueSpec clue) {
        this.clue = clue;
    }

    public static SearchClueDiscovered of(ClueSpec clue) {
        return new SearchClueDiscovered(clue);
    }
}
