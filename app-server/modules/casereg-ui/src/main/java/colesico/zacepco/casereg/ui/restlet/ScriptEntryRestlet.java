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
import colesico.zacepco.casereg.srv.service.ScriptEntryService;
import colesico.zacepco.casereg.ui.dto.ScriptOverview;
import colesico.zacepco.casereg.ui.dto.EntityOverview;
import colesico.zacepco.casereg.ui.dto.LocationOverview;
import colesico.zacepco.casereg.ui.dto.SceneOverview;

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
    @Authentication(WebJwt.class)
    public ScriptEntry createScriptEntry(@ParamName("script") HttpFile scriptFile) {
        return scriptEntryService.createScriptEntry(scriptFile.inputStream());
    }

    @Route("./")
    public List<ScriptEntry> listScriptEntries(Integer limit, Long offset) {
        return scriptEntryService.listScriptEntries(limit, offset);
    }

    @Route("./overview/:scriptEntryId")
    public ScriptOverview scriptOverview(Long scriptEntryId) {
        try {
            // var caseFile = caseFileService.findCaseFileById(caseFileId);
            var scriptPackage = scriptEntryService.scriptPackage(scriptEntryId);
            var script = scriptPackage.script().read();
            var scriptOverview = new ScriptOverview();

            scriptOverview.scriptEntryId = scriptEntryId;
            scriptOverview.metainfo = script.metainfo;

            for (var personage : script.setting.personages) {
                if (personage.hidden()) {
                    continue;
                }
                var personageOverview = new EntityOverview();
                personageOverview.id = personage.id.asString();
                personageOverview.name = personage.name;
                personageOverview.description = personage.description;
                scriptOverview.personages.add(personageOverview);
            }

            SceneOverview sceneOverview = new SceneOverview();
            scriptOverview.scene = sceneOverview;

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

            return scriptOverview;

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
