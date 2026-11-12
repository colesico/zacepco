package colesico.zacepco.docket.srv.jdbi;

import colesico.framework.jdbirec.mediators.EnumTextMediator;
import colesico.zacepco.docket.srv.model.ScriptStatus;

public class ScriptStatusMediator extends EnumTextMediator<ScriptStatus> {

    @Override
    protected ScriptStatus valueOf(String name) {
        return ScriptStatus.valueOf(name);
    }

}
