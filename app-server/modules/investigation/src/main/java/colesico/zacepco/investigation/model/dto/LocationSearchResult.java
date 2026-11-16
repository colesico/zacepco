package colesico.zacepco.investigation.model.dto;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;
import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.Set;

public record LocationSearchResult(
        ClueSpec clue,
        boolean accidental,
        Set<TId> matchedIds,
        Set<TId.Type> matchedTypes,
        Set<TId.Type> unmatchedTypes
) {
}
