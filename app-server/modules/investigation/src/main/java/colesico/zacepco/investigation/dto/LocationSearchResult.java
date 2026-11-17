package colesico.zacepco.investigation.dto;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;
import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.Set;

public class LocationSearchResult extends InvestigationActionResult {

    public boolean accidental;

    public LocationSearchResult() {
    }

    public LocationSearchResult(ClueSpec clue, Set<TId> matchedIds, Set<TId.Type> matchedTypes, Set<TId.Type> unmatchedTypes, boolean lastClue, boolean accidental) {
        super(clue, matchedIds, matchedTypes, unmatchedTypes, lastClue);
        this.accidental = accidental;
    }
}
