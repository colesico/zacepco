package colesico.zacepco.investigation.model.results;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;
import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.Set;

public final class ClueFocusDiscovered extends ClueDiscovered {

    /**
     * Entities matched between the detective and inspection table focus.
     */
    public final Set<TId> matchedIds;

    /**
     * Entities from the DETECTIVE focus unmatched with inspection table focus.
     */
    public final Set<TId> unmatchedIds;

    /**
     * Entity types matched between the detective and inspection table focus.
     */
    public final Set<TId.Type> matchedTypes;

    /**
     * Inspection TABLE entity types NOT matched with detective focus
     */
    public final Set<TId.Type> unmatchedTypes;

    private ClueFocusDiscovered(ClueSpec clue, Set<TId> matchedIds, Set<TId> unmatchedIds, Set<TId.Type> matchedTypes, Set<TId.Type> unmatchedTypes) {
        super(clue);
        this.matchedIds = matchedIds;
        this.unmatchedIds = unmatchedIds;
        this.matchedTypes = matchedTypes;
        this.unmatchedTypes = unmatchedTypes;
    }

    public static ClueFocusDiscovered of(ClueSpec clue, Set<TId> matchedIds, Set<TId> unmatchedIds, Set<TId.Type> matchedTypes, Set<TId.Type> unmatchedTypes) {
        return new ClueFocusDiscovered(clue, matchedIds, unmatchedIds, matchedTypes, unmatchedTypes);
    }
}