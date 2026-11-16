package colesico.zacepco.investigation.dto;

import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.Set;

public record DetectiveFocus(
        Set<TId> ids,
        Set<TId.Type> types
) {
}
