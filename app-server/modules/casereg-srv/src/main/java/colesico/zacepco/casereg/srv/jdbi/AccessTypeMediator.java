package colesico.zacepco.casereg.srv.jdbi;

import colesico.framework.jdbirec.mediators.EnumTextMediator;
import colesico.zacepco.casereg.srv.model.ScriptEntryAccessType;

public class AccessTypeMediator extends EnumTextMediator<ScriptEntryAccessType> {

    @Override
    protected ScriptEntryAccessType valueOf(String name) {
        return ScriptEntryAccessType.valueOf(name);
    }

}
