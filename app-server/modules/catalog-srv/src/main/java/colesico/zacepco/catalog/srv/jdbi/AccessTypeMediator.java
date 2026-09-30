package colesico.zacepco.catalog.srv.jdbi;

import colesico.framework.jdbirec.mediators.EnumMediator;
import colesico.zacepco.catalog.srv.model.ScriptAccessType;

public class AccessTypeMediator extends EnumMediator<ScriptAccessType> {

    @Override
    protected ScriptAccessType valueOf(String name) {
        return ScriptAccessType.valueOf(name);
    }

}
