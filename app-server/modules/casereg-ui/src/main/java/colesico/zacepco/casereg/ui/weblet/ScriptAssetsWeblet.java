package colesico.zacepco.casereg.ui.weblet;

import colesico.framework.http.HttpResponse;
import colesico.framework.httprouter.Route;
import colesico.framework.telehttp.ParamOrigin;
import colesico.framework.telehttp.origin.Origin;
import colesico.framework.weblet.Weblet;
import colesico.zacepco.casereg.srv.service.ScriptService;
import colesico.zacepco.casereg.srv.service.ScriptAssetsService;
import colesico.zacepco.respack.model.script.EntityId;
import colesico.zacepco.respack.pkg.PackageResource;
import colesico.zacepco.respack.pkg.ScriptPackage;
import jakarta.inject.Provider;

@Weblet
@Route("./assets")
public class ScriptAssetsWeblet {

    private final ScriptAssetsService scriptAssetsService;
    private final ScriptService scriptService;
    private final Provider<HttpResponse> httpResponse;

    public ScriptAssetsWeblet(ScriptAssetsService scriptAssetsService, ScriptService scriptService, Provider<HttpResponse> httpResponse) {
        this.scriptAssetsService = scriptAssetsService;
        this.scriptService = scriptService;
        this.httpResponse = httpResponse;
    }

    @Route("./:scriptEntryId/:assetId")
    @ParamOrigin(Origin.ROUTE)
    public void asset(Long scriptEntryId, String assetId) {

        ScriptPackage sp = scriptService.scriptPackage(scriptEntryId);

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
