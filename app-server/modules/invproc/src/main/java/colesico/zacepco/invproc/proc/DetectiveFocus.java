package colesico.zacepco.invproc.proc;

import colesico.zacepco.scriptpack.model.script.EntitySpec;

import java.util.Set;

public record DetectiveFocus(
        Set<EntitySpec.UID> ids,
        Set<EntitySpec.Type> types
) {
}
