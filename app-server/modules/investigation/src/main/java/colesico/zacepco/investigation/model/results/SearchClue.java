package colesico.zacepco.investigation.model.results;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;

public sealed class SearchClue
        extends ClueResult
        permits SearchFocusedClue {

    public SearchClue(ClueSpec clue) {
        super(clue);
    }

    public static SearchClue of(ClueSpec clue) {
        return new SearchClue(clue);
    }
}
