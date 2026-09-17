package colesico.zacepco.investigation.ui.restlet;

import colesico.framework.restlet.Restlet;
import colesico.zacepco.investigation.srv.service.ScriptRefService;

@Restlet
public class ScriptRefRestlet {
    private final ScriptRefService scriptRefService;

    public ScriptRefRestlet(ScriptRefService scriptRefService) {
        this.scriptRefService = scriptRefService;
    }

}
