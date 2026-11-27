package colesico.zacepco.investigation.model.results;

import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.Set;

public class InterrogationUnproductive extends InspectionResult {

    /**
     * Entities from detective focus not matched with table focus.
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

    public InterrogationUnproductive(Set<TId> unmatchedIds, Set<TId.Type> matchedTypes, Set<TId.Type> unmatchedTypes) {
        this.unmatchedIds = unmatchedIds;
        this.matchedTypes = matchedTypes;
        this.unmatchedTypes = unmatchedTypes;
    }
}
