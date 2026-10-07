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
import colesico.zacepco.cases.ui.dto.CaseFileDetails;
import colesico.zacepco.cases.ui.dto.EntityDetails;

import java.util.List;

@Restlet
@Route("./case-file")
public class CaseFileRestlet {
    private final CaseFileService caseFileService;

    public CaseFileRestlet(CaseFileService caseFileService) {
        this.caseFileService = caseFileService;
    }

    @RequestMethod(HttpMethod.POST)
    @Route("./")
    @Authentication(WebJwt.class)
    public CaseFile createCaseFile(@ParamName("script") HttpFile scriptFile) {
        return caseFileService.createCaseFile(scriptFile.inputStream());
    }

    @Route("./")
    public List<CaseFile> listCaseFiles(Integer limit, Long offset) {
        return caseFileService.listCaseFiles(limit, offset);
    }

    @Route("./details/:caseFileId")
    public CaseFileDetails details(Long caseFileId) {
        try {
            // var caseFile = caseFileService.findCaseFileById(caseFileId);
            var scriptPackage = caseFileService.scriptPackage(caseFileId);
            var script = scriptPackage.script().read();
            var details = new CaseFileDetails();

            details.caseFileId = caseFileId;
            details.meta = script.meta;

            for (var location : script.setting.scene.locations) {
                if (location.hidden) {
                    continue;
                }
                var entityDetails = new EntityDetails();
                entityDetails.id = location.id;
                entityDetails.name = location.name;
                entityDetails.description = location.description;
                details.locations.add(entityDetails);
            }

            return details;

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


}
