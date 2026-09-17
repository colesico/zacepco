package colesico.zacepco.investigation.ui.restlet;

import colesico.framework.httprouter.Route;
import colesico.framework.restlet.Restlet;
import colesico.zacepco.investigation.srv.model.ScriptRef;
import colesico.zacepco.investigation.srv.service.ScriptRefService;

import java.util.List;

@Restlet
@Route("./script-ref")
public class ScriptRefRestlet {
    private final ScriptRefService scriptRefService;

    public ScriptRefRestlet(ScriptRefService scriptRefService) {
        this.scriptRefService = scriptRefService;
    }

    public List<ScriptRef> lastScriptRefs(Integer limit, Long offset){
        return scriptRefService.lastScriptRefs(limit,offset);
    }
}
