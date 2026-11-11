package colesico.zacepco.investigation.dto;

import colesico.zacepco.script.model.scriptdoc.TypedId;

import java.util.Set;

public record DetectiveFocus(
        Set<TypedId> ids,
        Set<TypedId.Type> types
) {
}
