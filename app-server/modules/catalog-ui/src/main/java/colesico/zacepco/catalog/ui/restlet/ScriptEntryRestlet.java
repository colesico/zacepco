package colesico.zacepco.catalog.ui.restlet;

import colesico.framework.http.HttpFile;
import colesico.framework.http.HttpMethod;
import colesico.framework.httprouter.RequestMethod;
import colesico.framework.httprouter.Route;
import colesico.framework.restlet.Restlet;
import colesico.zacepco.catalog.srv.service.ScriptEntryService;

import java.util.List;

@Restlet
@Route("./script-entry")
public class ScriptEntryRestlet {
    private final ScriptEntryService scriptEntryService;

    public ScriptEntryRestlet(ScriptEntryService scriptEntryService) {
        this.scriptEntryService = scriptEntryService;
    }

    @RequestMethod(HttpMethod.POST)
    @Route("./")
    public colesico.zacepco.catalog.srv.model.ScriptEntry addScriptEntry(HttpFile script) {
        return null;
    }

    @Route("./")
    public List<colesico.zacepco.catalog.srv.model.ScriptEntry> listScriptEntries(Integer limit, Long offset) {
        return scriptEntryService.listScriptEntries(limit, offset);
    }
}
