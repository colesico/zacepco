package colesico.zacepco.docket.srv.jdbi;

import colesico.framework.jdbirec.mediators.EnumTextMediator;
import colesico.zacepco.docket.srv.model.ScriptEntryAccessType;

public class AccessTypeMediator extends EnumTextMediator<ScriptEntryAccessType> {

    @Override
    protected ScriptEntryAccessType valueOf(String name) {
        return ScriptEntryAccessType.valueOf(name);
    }

}
