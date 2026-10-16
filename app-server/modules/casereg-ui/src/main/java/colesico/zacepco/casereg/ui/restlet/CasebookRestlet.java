package colesico.zacepco.casereg.ui.restlet;

import colesico.framework.http.HttpFile;
import colesico.framework.http.HttpMethod;
import colesico.framework.httprouter.RequestMethod;
import colesico.framework.httprouter.Route;
import colesico.framework.jjwt.WebJwt;
import colesico.framework.restlet.Restlet;
import colesico.framework.security.authentication.Authentication;
import colesico.framework.telehttp.ParamName;
import colesico.zacepco.casereg.srv.model.Casebook;
import colesico.zacepco.casereg.srv.service.CasebookService;
import colesico.zacepco.casereg.ui.dto.CasebookOverview;
import colesico.zacepco.casereg.ui.dto.EntityOverview;
import colesico.zacepco.casereg.ui.dto.LocationOverview;
import colesico.zacepco.casereg.ui.dto.SceneOverview;

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
            casebookOverview.metainfo = script.metainfo;

            for (var personage : script.setting.personages) {
                if (personage.hidden()) {
                    continue;
                }
                var personageOverview = new EntityOverview();
                personageOverview.id = personage.id.asString();
                personageOverview.name = personage.name;
                personageOverview.description = personage.description;
                casebookOverview.personages.add(personageOverview);
            }

            SceneOverview sceneOverview = new SceneOverview();
            casebookOverview.scene = sceneOverview;

            sceneOverview.cols = script.setting.scene.size.cols;
            sceneOverview.rows = script.setting.scene.size.rows;

            for (var location : script.setting.scene.locations) {
                if (location.isHidden()) {
                    continue;
                }
                var locationOverview = new LocationOverview();
                locationOverview.id = location.id.asString();
                locationOverview.name = location.name;
                locationOverview.description = location.description;
                locationOverview.col = location.pos.col;
                locationOverview.row = location.pos.row;
                sceneOverview.locations.add(locationOverview);
            }

            return casebookOverview;

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
