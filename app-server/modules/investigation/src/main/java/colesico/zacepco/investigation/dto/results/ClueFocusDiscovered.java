package colesico.zacepco.investigation.dto.results;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;
import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.Set;

public final class ClueFocusDiscovered extends ClueDiscovered {

    public Set<TId> matchedIds;
    public Set<TId.Type> matchedTypes;
    public Set<TId.Type> unmatchedTypes;

    public ClueFocusDiscovered(ClueSpec clue,
                               Set<TId> matchedIds, Set<TId.Type> matchedTypes, Set<TId.Type> unmatchedTypes,
                               Integer cluesRemaining, Integer awardedPoints
    ) {
        super("CLUE_FOCUS_DISCOVERED", clue, cluesRemaining, awardedPoints);
        this.matchedIds = matchedIds;
        this.matchedTypes = matchedTypes;
        this.unmatchedTypes = unmatchedTypes;
    }

}
