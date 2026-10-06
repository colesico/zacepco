package colesico.zacepco.cases.ui.weblet;

import colesico.framework.http.HttpResponse;
import colesico.framework.httprouter.Route;
import colesico.framework.telehttp.ParamOrigin;
import colesico.framework.telehttp.origin.Origin;
import colesico.framework.weblet.Weblet;
import colesico.zacepco.cases.srv.service.CaseFileService;
import colesico.zacepco.cases.srv.service.ScriptAssetsService;
import colesico.zacepco.script.model.setting.EntityId;
import colesico.zacepco.script.pkg.PackageResource;
import colesico.zacepco.script.pkg.ScriptPackage;
import jakarta.inject.Provider;

@Weblet
@Route("./assets")
public class ScriptAssetsWeblet {

    private final ScriptAssetsService scriptAssetsService;
    private final CaseFileService caseFileService;
    private final Provider<HttpResponse> httpResponse;

    public ScriptAssetsWeblet(ScriptAssetsService scriptAssetsService, CaseFileService caseFileService, Provider<HttpResponse> httpResponse) {
        this.scriptAssetsService = scriptAssetsService;
        this.caseFileService = caseFileService;
        this.httpResponse = httpResponse;
    }

    @Route("./:scriptEntryId/:assetId")
    @ParamOrigin(Origin.ROUTE)
    public void asset(Long scriptEntryId, String assetId) {

        ScriptPackage sp = caseFileService.scriptPackage(scriptEntryId);

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
