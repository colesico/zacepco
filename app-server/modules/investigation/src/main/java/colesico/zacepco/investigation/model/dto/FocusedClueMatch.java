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
         * Inspection table entities matched with detective focus
         */
        Set<TId> matchedIds,

        /**
         * Inspection table entity types matched with detective focus
         */
        Set<TId.Type> matchedTypes,

        /**
         * Inspection table entity types NOT matched with detective focus
         */
        Set<TId.Type> unmatchedTypes
) {
}

