package colesico.zacepco.docket.ui.restlet;

import colesico.framework.http.HttpFile;
import colesico.framework.http.HttpMethod;
import colesico.framework.httprouter.RequestMethod;
import colesico.framework.httprouter.Route;
import colesico.framework.jjwt.WebJwt;
import colesico.framework.restlet.Restlet;
import colesico.framework.security.authentication.Authentication;
import colesico.framework.telehttp.ParamName;
import colesico.zacepco.docket.srv.model.ScriptEntry;
import colesico.zacepco.docket.srv.service.ScriptService;
import colesico.zacepco.script.model.scriptdoc.ScriptDocument;

import java.util.List;

@Restlet
@Route("./script")
public class ScriptRestlet {
    private final ScriptService scriptService;

    public ScriptRestlet(ScriptService scriptService) {
        this.scriptService = scriptService;
    }

    @RequestMethod(HttpMethod.POST)
    @Route("./")
    @Authentication(WebJwt.class)
    public ScriptEntry createScriptEntry(@ParamName("script") HttpFile scriptFile) {
        return scriptService.createScriptEntry(scriptFile.inputStream());
    }

    @Route("./")
    public List<ScriptEntry> listScriptEntries(Integer limit, Long offset) {
        return scriptService.listScriptEntries(limit, offset);
    }

    @Route("./entry/:scriptEntryId/document")
    public ScriptDocument scriptDocument(Long scriptEntryId) {
        var scriptPackage = scriptService.scriptPackage(scriptEntryId);
        var scriptDoc = scriptPackage.script().read();
        return scriptDoc;
    }

}
