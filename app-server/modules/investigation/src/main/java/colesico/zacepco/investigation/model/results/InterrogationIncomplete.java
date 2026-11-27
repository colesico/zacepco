package colesico.zacepco.investigation.model.results;

import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.Set;

public class InterrogationIncomplete extends InspectionResult {

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

    public InterrogationIncomplete(Set<TId> matchedIds, Set<TId.Type> matchedTypes, Set<TId.Type> unmatchedTypes) {
        this.matchedIds = matchedIds;
        this.matchedTypes = matchedTypes;
        this.unmatchedTypes = unmatchedTypes;
    }
}
