package colesico.zacepco.investigation.dto;

import colesico.zacepco.script.model.scriptdoc.FocusedClueSpec;
import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.Set;

public record FocusedClueMatch(
        FocusedClueSpec focusedClue,
        long eq,
        long eqt,
        long ne,
        Set<TId> matchedIds,
        Set<TId.Type> matchedTypes,
        Set<TId.Type> unmatchedTypes
) {
}

