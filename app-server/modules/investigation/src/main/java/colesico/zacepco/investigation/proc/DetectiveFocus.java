package colesico.zacepco.investigation.proc;

import colesico.zacepco.script.model.scriptdoc.EntitySpec;

import java.util.Set;

public record DetectiveFocus(
        Set<EntitySpec.UID> ids,
        Set<EntitySpec.Type> types
) {
}
