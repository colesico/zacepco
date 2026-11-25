package colesico.zacepco.investigation.model.results;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;
import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.Set;

public final class ClueFocusDiscovered extends ClueDiscovered {

    /**
     * Inspection table entities matched with detective focus
     */
    public final Set<TId> matchedIds;

    /**
     * Inspection table entity types matched with detective focus
     */
    public final Set<TId.Type> matchedTypes;

    /**
     * Inspection table entity types NOT matched with detective focus
     */
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