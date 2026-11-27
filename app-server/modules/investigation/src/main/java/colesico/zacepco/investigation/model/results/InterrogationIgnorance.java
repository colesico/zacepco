package colesico.zacepco.investigation.model.results;

import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.Set;

/**
 * Represents an interrogation result indicating that the subject
 * has no knowledge or awareness of the interrogation subject matter.
 */
public class InterrogationIgnorance extends InspectionResult {

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

    public InterrogationIgnorance(Set<TId> unmatchedIds, Set<TId.Type> matchedTypes, Set<TId.Type> unmatchedTypes) {
        this.unmatchedIds = unmatchedIds;
        this.matchedTypes = matchedTypes;
        this.unmatchedTypes = unmatchedTypes;
    }
}
