package colesico.zacepco.casereg.ui.restlet;

import colesico.framework.http.HttpFile;
import colesico.framework.http.HttpMethod;
import colesico.framework.httprouter.RequestMethod;
import colesico.framework.httprouter.Route;
import colesico.framework.jjwt.WebJwt;
import colesico.framework.restlet.Restlet;
import colesico.framework.security.authentication.Authentication;
import colesico.framework.telehttp.ParamName;
import colesico.zacepco.casereg.srv.model.ScriptEntry;
import colesico.zacepco.casereg.srv.service.ScriptService;
import colesico.zacepco.casereg.ui.dto.ScriptOverview;
import colesico.zacepco.casereg.ui.dto.EntityOverview;
import colesico.zacepco.casereg.ui.dto.LocationOverview;
import colesico.zacepco.casereg.ui.dto.SceneOverview;

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

    @Route("./overview/:scriptEntryId")
    public ScriptOverview scriptOverview(Long scriptEntryId) {
        try {
            // var caseFile = caseFileService.findCaseFileById(caseFileId);
            var scriptPackage = scriptService.resourcePackage(scriptEntryId);
            var script = scriptPackage.script().read();
            var scriptOverview = new ScriptOverview();

            scriptOverview.scriptEntryId = scriptEntryId;
            scriptOverview.metainfo = script.metainfo;

            for (var personage : script.setting.personages) {
                if (personage.isHidden()) {
                    continue;
                }
                var persOverview = new EntityOverview();
                persOverview.id = personage.id.asString();
                persOverview.asset = personage.asset().asString();
                persOverview.name = personage.name;
                persOverview.description = personage.description;
                scriptOverview.personages.add(persOverview);
            }

            for (var detective : script.setting.detectives) {
                var detOverview = new EntityOverview();
                detOverview.id = detective.id.asString();
                detOverview.asset = detective.asset().asString();
                detOverview.name = detective.name;
                detOverview.description = detective.description;
                scriptOverview.detectives.add(detOverview);
            }

            SceneOverview sceneOverview = new SceneOverview();
            scriptOverview.scene = sceneOverview;

            sceneOverview.cols = script.setting.scene.size.cols;
            sceneOverview.rows = script.setting.scene.size.rows;

            for (var location : script.setting.scene.locations) {
                if (location.isHidden()) {
                    continue;
                }
                var locOverview = new LocationOverview();
                locOverview.id = location.id.asString();
                locOverview.asset = location.asset().asString();
                locOverview.name = location.name;
                locOverview.description = location.description;
                locOverview.col = location.pos.col;
                locOverview.row = location.pos.row;
                sceneOverview.locations.add(locOverview);
            }

            return scriptOverview;

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
