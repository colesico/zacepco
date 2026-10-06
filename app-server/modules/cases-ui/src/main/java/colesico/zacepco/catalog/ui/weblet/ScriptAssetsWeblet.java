package colesico.zacepco.catalog.ui.weblet;

import colesico.framework.http.HttpResponse;
import colesico.framework.httprouter.Route;
import colesico.framework.telehttp.ParamOrigin;
import colesico.framework.telehttp.origin.Origin;
import colesico.framework.weblet.Weblet;
import colesico.zacepco.cases.srv.service.ScriptAssetsService;
import colesico.zacepco.cases.srv.service.ScriptEntryService;
import colesico.zacepco.script.model.setting.EntityId;
import colesico.zacepco.script.pkg.PackageResource;
import colesico.zacepco.script.pkg.ScriptPackage;
import jakarta.inject.Provider;

@Weblet
@Route("/catalog/assets")
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

        PackageResource res;
        if (assetId.equals("S")) {
            res = sp.scriptImage();
        } else {
            var entityId = EntityId.parse(assetId);
            res = sp.entityImage(entityId);
        }

        scriptAssetsService.readAsset(scriptEntryId, res, httpResponse.get().outputStream());
    }
}
