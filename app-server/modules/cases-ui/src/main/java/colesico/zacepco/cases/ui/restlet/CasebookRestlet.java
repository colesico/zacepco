package colesico.zacepco.cases.ui.restlet;

import colesico.framework.http.HttpFile;
import colesico.framework.http.HttpMethod;
import colesico.framework.httprouter.RequestMethod;
import colesico.framework.httprouter.Route;
import colesico.framework.jjwt.WebJwt;
import colesico.framework.restlet.Restlet;
import colesico.framework.security.authentication.Authentication;
import colesico.framework.telehttp.ParamName;
import colesico.zacepco.cases.srv.model.Casebook;
import colesico.zacepco.cases.srv.service.CasebookService;
import colesico.zacepco.cases.ui.dto.CasebookDemo;
import colesico.zacepco.cases.ui.dto.EntityDemo;

import java.util.List;

@Restlet
@Route("./casebook")
public class CasebookRestlet {
    private final CasebookService casebookService;

    public CasebookRestlet(CasebookService casebookService) {
        this.casebookService = casebookService;
    }

    @RequestMethod(HttpMethod.POST)
    @Route("./")
    @Authentication(WebJwt.class)
    public Casebook createCasebook(@ParamName("script") HttpFile scriptFile) {
        return casebookService.createCasebook(scriptFile.inputStream());
    }

    @Route("./")
    public List<Casebook> listCasebooks(Integer limit, Long offset) {
        return casebookService.listCasebooks(limit, offset);
    }

    @Route("./demo/:casebookId")
    public CasebookDemo demo(Long casebookId) {
        try {
            // var caseFile = caseFileService.findCaseFileById(caseFileId);
            var scriptPackage = casebookService.scriptPackage(casebookId);
            var script = scriptPackage.script().read();
            var casebookDemo = new CasebookDemo();

            casebookDemo.caseFileId = casebookId;
            casebookDemo.meta = script.meta;

            for (var location : script.setting.scene.locations) {
                if (location.hidden) {
                    continue;
                }
                var entityDetails = new EntityDemo();
                entityDetails.id = location.id;
                entityDetails.name = location.name;
                entityDetails.description = location.description;
                casebookDemo.locations.add(entityDetails);
            }

            return casebookDemo;

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
