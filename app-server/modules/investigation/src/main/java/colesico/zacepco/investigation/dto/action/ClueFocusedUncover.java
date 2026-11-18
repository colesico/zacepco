package colesico.zacepco.investigation.dto.action;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;
import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.Set;

public final class ClueFocusedUncover extends ClueUncover {

    public Set<TId> matchedIds;
    public Set<TId.Type> matchedTypes;
    public Set<TId.Type> unmatchedTypes;

    public ClueFocusedUncover(ClueSpec clue,
                              Set<TId> matchedIds, Set<TId.Type> matchedTypes, Set<TId.Type> unmatchedTypes,
                              Integer cluesRemaining, Integer awardedPoints
    ) {
        super(clue, cluesRemaining, awardedPoints);
        this.matchedIds = matchedIds;
        this.matchedTypes = matchedTypes;
        this.unmatchedTypes = unmatchedTypes;
    }

    @Override
    public String code() {
        return "CLUE_FOCUSED_UNCOVER";
    }
}
