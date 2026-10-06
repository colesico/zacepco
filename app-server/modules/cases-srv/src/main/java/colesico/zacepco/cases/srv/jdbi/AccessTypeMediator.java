package colesico.zacepco.cases.srv.jdbi;

import colesico.framework.jdbirec.mediators.EnumMediator;
import colesico.zacepco.cases.srv.model.ScriptAccessType;

public class AccessTypeMediator extends EnumMediator<ScriptAccessType> {

    @Override
    protected ScriptAccessType valueOf(String name) {
        return ScriptAccessType.valueOf(name);
    }

}
