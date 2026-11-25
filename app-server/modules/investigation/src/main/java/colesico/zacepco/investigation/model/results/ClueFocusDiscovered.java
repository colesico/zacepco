package colesico.zacepco.investigation.model.results;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;
import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.Set;

public final class ClueFocusDiscovered extends ClueDiscovered {

    public final Set<TId> matchedIds;
    public final Set<TId.Type> matchedTypes;
    public final Set<TId.Type> unmatchedTypes;

    private ClueFocusDiscovered(ClueSpec clue, Set<TId> matchedIds, Set<TId.Type> matchedTypes, Set<TId.Type> unmatchedTypes) {
        super(clue);
        this.matchedIds = matchedIds;
        this.matchedTypes = matchedTypes;
        this.unmatchedTypes = unmatchedTypes;
    }

    public static ClueFocusDiscovered of(ClueSpec clue, Set<TId> matchedIds, Set<TId.Type> matchedTypes, Set<TId.Type> unmatchedTypes) {
        return new ClueFocusDiscovered(clue, matchedIds, matchedTypes, unmatchedTypes);
    }
}