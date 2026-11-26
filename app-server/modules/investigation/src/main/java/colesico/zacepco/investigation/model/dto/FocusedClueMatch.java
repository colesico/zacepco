package colesico.zacepco.investigation.model.dto;

import colesico.zacepco.script.model.scriptdoc.FocusedClueSpec;
import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.Set;

public record FocusedClueMatch(
        FocusedClueSpec focusedClue,
        long eq,
        long eqt,
        long ne,

        /**
         * Entities matched between the detective and inspection table focus.
         */
        Set<TId> matchedIds,

        /**
         * Entities from the DETECTIVE focus unmatched with inspection table focus.
         */
        Set<TId> unmatchedIds,

        /**
         * Entity types matched between the detective and inspection table focus.
         */
        Set<TId.Type> matchedTypes,

        /**
         * Inspection table entity types NOT matched with detective focus
         */
        Set<TId.Type> unmatchedTypes
) {
}

