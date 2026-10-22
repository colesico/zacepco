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

    @Route("./image/:scriptEntryId/:assetKey/:scaleKey")
    @ParamOrigin(Origin.ROUTE)
    public void image(Long scriptEntryId, String assetKey, String scaleKey) {

        ScriptPackage sp = scriptService.scriptPackage(scriptEntryId);

        var res = switch (assetKey) {
            case "S" -> sp.scriptImage();
            default -> {
                var entityId = Entity.UID.parse(assetKey);
                yield sp.entityImage(entityId);
            }
        };

        var scale = switch (scaleKey) {
            case "thumb" -> ImageScale.THUMB;
            case "medium" -> ImageScale.MEDIUM;
        }

        try (
                var is = scriptAssetsService.imageInput(scriptEntryId, res, ImageScale.THUMB);
        ) {
            is.transferTo(httpResponse.get().outputStream());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
