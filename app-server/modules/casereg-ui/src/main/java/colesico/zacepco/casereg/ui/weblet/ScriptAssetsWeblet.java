package colesico.zacepco.casereg.ui.weblet;

import colesico.framework.http.HttpResponse;
import colesico.framework.httprouter.Route;
import colesico.framework.telehttp.ParamOrigin;
import colesico.framework.telehttp.origin.Origin;
import colesico.framework.weblet.Weblet;
import colesico.zacepco.casereg.srv.service.ScriptEntryService;
import colesico.zacepco.casereg.srv.service.ScriptAssetsService;
import colesico.zacepco.script.model.setting.EntityId;
import colesico.zacepco.script.pkg.PackageResource;
import colesico.zacepco.script.pkg.ScriptPackage;
import jakarta.inject.Provider;

@Weblet
@Route("./assets")
public class ScriptAssetsWeblet {

    private final ScriptAssetsService scriptAssetsService;
    private final ScriptEntryService scriptEntryService;
    private final Provider<HttpResponse> httpResponse;

    public ScriptAssetsWeblet(ScriptAssetsService scriptAssetsService, ScriptEntryService scriptEntryService, Provider<HttpResponse> httpResponse) {
        this.scriptAssetsService = scriptAssetsService;
        this.scriptEntryService = scriptEntryService;
        this.httpResponse = httpResponse;
    }

    @Route("./:scriptEntryId/:assetId")
    @ParamOrigin(Origin.ROUTE)
    public void asset(Long scriptEntryId, String assetId) {

        ScriptPackage sp = scriptEntryService.scriptPackage(scriptEntryId);

        PackageResource res = switch (assetId) {
            case "S" -> sp.scriptImage();
            default -> {
                var entityId = EntityId.parse(assetId);
                yield sp.entityImage(entityId);
            }
        };

        scriptAssetsService.readAsset(scriptEntryId, res, httpResponse.get().outputStream());
    }
}
