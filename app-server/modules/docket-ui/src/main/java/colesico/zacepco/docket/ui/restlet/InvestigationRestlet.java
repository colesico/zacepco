package colesico.zacepco.docket.ui.restlet;

import colesico.framework.http.HttpMethod;
import colesico.framework.httprouter.RequestMethod;
import colesico.framework.httprouter.Route;
import colesico.framework.jjwt.WebJwt;
import colesico.framework.restlet.Restlet;
import colesico.framework.security.authentication.Authentication;
import colesico.framework.service.BeanField;
import colesico.framework.telehttp.ParamName;
import colesico.framework.telehttp.ParamOrigin;
import colesico.framework.telehttp.origin.Origin;
import colesico.framework.telehttp.result.ValueResult;
import colesico.zacepco.docket.srv.model.dto.CreateInvestigationRequest;
import colesico.zacepco.docket.srv.model.Investigation;
import colesico.zacepco.docket.srv.service.InvestigationService;
import colesico.zacepco.docket.srv.service.ScriptService;
import colesico.zacepco.docket.ui.dto.InvestigationContext;
import colesico.zacepco.docket.ui.dto.InspectionResponse;
import colesico.zacepco.investigation.model.DetectiveFocus;
import colesico.zacepco.script.model.scriptdoc.TId;

@Restlet
@Route("./investigation")
public class InvestigationRestlet {

    private final InvestigationService investigationService;
    private final ScriptService scriptService;

    public InvestigationRestlet(InvestigationService investigationService, ScriptService scriptService) {
        this.investigationService = investigationService;
        this.scriptService = scriptService;
    }

    @Authentication(WebJwt.class)
    @Route("./personal")
    public Investigation findPersonalInvestigation(@ParamOrigin(Origin.QUERY) @ParamName("script") Long scriptId) {
        var invOpt = investigationService.findPersonalInvestigation(scriptId);
        return invOpt.orElse(null);
    }

    @RequestMethod(HttpMethod.POST)
    @Route("./new")
    @Authentication(WebJwt.class)
    public Investigation createInvestigation(@ParamOrigin(Origin.BODY) CreateInvestigationRequest request) {
        return investigationService.createInvestigation(request);
    }

    @Route("./:id/context")
    public ValueResult<InvestigationContext> findInvestigationContext(@ParamOrigin(Origin.ROUTE) Long id) {
        var invOpt = investigationService.findInvestigationById(id);

        if (invOpt.isPresent()) {
            var inv = invOpt.get();
            var ctx = new InvestigationContext();
            ctx.investigation = inv;
            ctx.script = scriptService.scriptPackage(inv.scriptId).scriptDocument().read();
            return ValueResult.value(ctx).build();
        } else {
            return ValueResult.empty(InvestigationContext.class).status(204).build();
        }
    }

    @Route("./:investigationId/search/:entityId")
    @RequestMethod(HttpMethod.PUT)
    public InspectionResponse entitySearch(
            @ParamOrigin(Origin.ROUTE) Long investigationId,
            @ParamOrigin(Origin.ROUTE) String entityId,
            @BeanField String focus,
            @BeanField Integer d12) {

        var detectiveFocus = DetectiveFocus.parse(focus);
        TId tId = TId.parse(entityId);
        var result = investigationService
                .search(investigationId, tId, detectiveFocus, d12);

        return new InspectionResponse(result);
    }
}
