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
import colesico.zacepco.cases.ui.dto.CasebookOverview;
import colesico.zacepco.cases.ui.dto.EntityOverview;

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

    @Route("./overview/:casebookId")
    public CasebookOverview casebookOverview(Long casebookId) {
        try {
            // var caseFile = caseFileService.findCaseFileById(caseFileId);
            var scriptPackage = casebookService.scriptPackage(casebookId);
            var script = scriptPackage.script().read();
            var casebookOverview = new CasebookOverview();

            casebookOverview.casebookId = casebookId;
            casebookOverview.meta = script.meta;

            for (var location : script.setting.scene.locations) {
                if (location.hidden()) {
                    continue;
                }
                var entityOverview = new EntityOverview();
                entityOverview.id = location.id;
                entityOverview.name = location.name;
                entityOverview.description = location.description;
                casebookOverview.locations.add(entityOverview);
            }

            for (var personage : script.setting.personages) {
                if (personage.hidden()) {
                    continue;
                }
                var entityOverview = new EntityOverview();
                entityOverview.id = personage.id;
                entityOverview.name = personage.name;
                entityOverview.description = personage.description;
                casebookOverview.personages.add(entityOverview);
            }

            return casebookOverview;

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
