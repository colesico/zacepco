package colesico.zacepco.cases.ui.restlet;

import colesico.framework.http.HttpFile;
import colesico.framework.http.HttpMethod;
import colesico.framework.httprouter.RequestMethod;
import colesico.framework.httprouter.Route;
import colesico.framework.jjwt.WebJwt;
import colesico.framework.restlet.Restlet;
import colesico.framework.security.authentication.Authentication;
import colesico.framework.telehttp.ParamName;
import colesico.zacepco.cases.srv.model.CaseFile;
import colesico.zacepco.cases.srv.service.CaseFileService;

import java.util.List;

@Restlet
@Route("./script-entry")
public class CaseFileRestlet {
    private final CaseFileService caseFileService;

    public CaseFileRestlet(CaseFileService caseFileService) {
        this.caseFileService = caseFileService;
    }

    @RequestMethod(HttpMethod.POST)
    @Route("./")
    @Authentication(WebJwt.class)
    public CaseFile addScript(@ParamName("script") HttpFile scriptFile) {
        return caseFileService.createCaseFile(scriptFile.inputStream());
    }

    @Route("./")
    public List<CaseFile> listScriptEntries(Integer limit, Long offset) {
        return caseFileService.listCaseFiles(limit, offset);
    }
}
