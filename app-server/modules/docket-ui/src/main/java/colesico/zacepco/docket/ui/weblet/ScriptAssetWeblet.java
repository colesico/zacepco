package colesico.zacepco.docket.ui.weblet;

import colesico.framework.http.HttpResponse;
import colesico.framework.httprouter.Route;
import colesico.framework.telehttp.ParamOrigin;
import colesico.framework.telehttp.origin.Origin;
import colesico.framework.weblet.Weblet;
import colesico.zacepco.docket.srv.model.ImageScale;
import colesico.zacepco.docket.srv.service.ScriptAssetService;
import colesico.zacepco.docket.srv.service.ScriptService;
import colesico.zacepco.script.model.scriptdoc.TId;
import colesico.zacepco.script.pkg.ScriptPackage;
import jakarta.inject.Provider;

@Weblet
@Route("./script")
public class ScriptAssetWeblet {

    private final ScriptAssetService scriptAssetService;
    private final ScriptService scriptService;
    private final Provider<HttpResponse> httpResponse;

    public ScriptAssetWeblet(ScriptAssetService scriptAssetService, ScriptService scriptService, Provider<HttpResponse> httpResponse) {
        this.scriptAssetService = scriptAssetService;
        this.scriptService = scriptService;
        this.httpResponse = httpResponse;
    }

    @Route("./images/:scaleCode/:scriptId/:asset")
    @ParamOrigin(Origin.ROUTE)
    public void image(String scaleCode, Long scriptId, String asset) {

        ScriptPackage sp = scriptService.scriptPackage(scriptId);

        var res = switch (asset) {
            case "S" -> sp.scriptImage();
            default -> {
                var entityId = TId.parse(asset);
                yield sp.entityImage(entityId);
            }
        };

        var scale = ImageScale.parse(scaleCode);
        if (scale == null) {
            throw new IllegalStateException("Unsupported image scale:" + scaleCode);
        }

        try (
                var is = scriptAssetService.imageInput(scriptId, res, scale);
        ) {
            is.transferTo(httpResponse.get().outputStream());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
