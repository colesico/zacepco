package colesico.zacepco.investigation.model.results;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;
import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.Set;

public final class SearchFocusedClueDiscovered extends SearchClueDiscovered {

    /**
     * Entities matched between the detective and inspection table focus.
     */
    public final Set<TId> matchedIds;

    /**
     * Entity types matched between the detective and inspection table focus.
     */
    public final Set<TId.Type> matchedTypes;

    /**
     * Inspection TABLE entity types NOT matched with detective focus
     */
    public final Set<TId.Type> unmatchedTypes;

    private SearchFocusedClueDiscovered(ClueSpec clue, Set<TId> matchedIds, Set<TId.Type> matchedTypes, Set<TId.Type> unmatchedTypes) {
        super(clue);
        this.matchedIds = matchedIds;
        this.matchedTypes = matchedTypes;
        this.unmatchedTypes = unmatchedTypes;
    }

    public static SearchFocusedClueDiscovered of(ClueSpec clue, Set<TId> matchedIds, Set<TId.Type> matchedTypes, Set<TId.Type> unmatchedTypes) {
        return new SearchFocusedClueDiscovered(clue, matchedIds, matchedTypes, unmatchedTypes);
    }
}