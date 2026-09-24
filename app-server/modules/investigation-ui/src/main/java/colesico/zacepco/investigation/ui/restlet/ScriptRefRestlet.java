package colesico.zacepco.investigation.ui.restlet;

import colesico.framework.httprouter.Route;
import colesico.framework.restlet.Restlet;
import colesico.zacepco.investigation.srv.model.ScriptEntry;
import colesico.zacepco.investigation.srv.service.ScriptCatalogService;

import java.util.List;

@Restlet
@Route("./script-ref")
public class ScriptRefRestlet {
    private final ScriptCatalogService scriptCatalogService;

    public ScriptRefRestlet(ScriptCatalogService scriptCatalogService) {
        this.scriptCatalogService = scriptCatalogService;
    }

    public List<ScriptEntry> lastScriptRefs(Integer limit, Long offset){
        return scriptCatalogService.lastScripts(limit,offset);
    }
}
