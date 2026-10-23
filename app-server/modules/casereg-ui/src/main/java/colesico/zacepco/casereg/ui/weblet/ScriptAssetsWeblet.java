package colesico.zacepco.casereg.ui.weblet;

import colesico.framework.http.HttpResponse;
import colesico.framework.httprouter.Route;
import colesico.framework.telehttp.ParamOrigin;
import colesico.framework.telehttp.origin.Origin;
import colesico.framework.weblet.Weblet;
import colesico.zacepco.casereg.srv.model.ImageScale;
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

    @Route("./image/:scaleCode/:scriptEntryId/:assetUid")
    @ParamOrigin(Origin.ROUTE)
    public void image(String scaleCode, Long scriptEntryId, String assetUid) {

        ScriptPackage sp = scriptService.scriptPackage(scriptEntryId);

        var res = switch (assetUid) {
            case "S" -> sp.scriptImage();
            default -> {
                var uid = Entity.UID.parse(assetUid);
                yield sp.entityImage(uid);
            }
        };

        var scale = ImageScale.parse(scaleCode);
        if (scale == null) {
            throw new IllegalStateException("Unsupported image scale:" + scaleCode);
        }

        try (
                var is = scriptAssetsService.imageInput(scriptEntryId, res, scale);
        ) {
            is.transferTo(httpResponse.get().outputStream());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
