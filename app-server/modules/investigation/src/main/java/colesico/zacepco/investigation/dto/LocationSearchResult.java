package colesico.zacepco.investigation.dto;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;
import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.Set;

import static colesico.zacepco.investigation.dto.InvestigationActionResult.Status.OK;

public class LocationSearchResult extends InvestigationActionResult {

    public boolean accidental;

    public LocationSearchResult(Status status) {
        super(status);
    }

    public static LocationSearchResult error(Status status) {
        return new LocationSearchResult(status);
    }

    /**
     * Detective focus matched clue result
     */
    public static LocationSearchResult matchedClue(ClueSpec clue, Set<TId> matchedIds, Set<TId.Type> matchedTypes, Set<TId.Type> unmatchedTypes, int cluesRemaining) {
        var result = new LocationSearchResult(OK);
        result.accidental = false;
        result.clue = clue;
        result.matchedIds = matchedIds;
        result.matchedTypes = matchedTypes;
        result.unmatchedTypes = unmatchedTypes;
        result.cluesRemaining = cluesRemaining;
        return result;
    }

    /**
     * Accidental discovery clue
     */
    public static LocationSearchResult accidentalClue(ClueSpec clue, int cluesRemaining) {
        var result = new LocationSearchResult(OK);
        result.accidental = true;
        result.clue = clue;
        result.cluesRemaining = cluesRemaining;
        return result;
    }
}
