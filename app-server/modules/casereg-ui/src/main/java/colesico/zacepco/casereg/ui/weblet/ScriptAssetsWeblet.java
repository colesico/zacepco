package colesico.zacepco.casereg.ui.weblet;

import colesico.framework.http.HttpResponse;
import colesico.framework.httprouter.Route;
import colesico.framework.telehttp.ParamOrigin;
import colesico.framework.telehttp.origin.Origin;
import colesico.framework.weblet.Weblet;
import colesico.zacepco.casereg.srv.service.ScriptService;
import colesico.zacepco.casereg.srv.service.ScriptAssetsService;
import colesico.zacepco.scriptpack.model.script.Entity;
import colesico.zacepco.scriptpack.pkg.ScriptPackage;
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

        ScriptPackage sp = scriptService.resourcePackage(scriptEntryId);

        var res = switch (assetId) {
            case "S" -> sp.scriptImage();
            default -> {
                var entityId = Entity.UID.parse(assetId);
                yield sp.entityImage(entityId);
            }
        };

        scriptAssetsService.readAsset(scriptEntryId, res, httpResponse.get().outputStream());
    }
}
