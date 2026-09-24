package colesico.zacepco.investigation.ui.restlet;

import colesico.framework.httprouter.Route;
import colesico.framework.restlet.Restlet;
import colesico.framework.weblet.assist.UploadedFile;
import colesico.zacepco.investigation.srv.model.ScriptEntry;
import colesico.zacepco.investigation.srv.service.ScriptCatalogService;

import java.util.List;

@Restlet
@Route("./script-catalog")
public class ScriptCatalogRestlet {
    private final ScriptCatalogService scriptCatalogService;

    public ScriptCatalogRestlet(ScriptCatalogService scriptCatalogService) {
        this.scriptCatalogService = scriptCatalogService;
    }

    public ScriptEntry addScript(UploadedFile uploadedFile)

    public List<ScriptEntry> lastScriptRefs(Integer limit, Long offset){
        return scriptCatalogService.lastScripts(limit,offset);
    }
}
