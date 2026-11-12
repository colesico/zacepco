package colesico.zacepco.docket.ui.restlet;

import colesico.framework.http.HttpMethod;
import colesico.framework.httprouter.RequestMethod;
import colesico.framework.httprouter.Route;
import colesico.framework.jjwt.WebJwt;
import colesico.framework.restlet.Restlet;
import colesico.framework.security.authentication.Authentication;
import colesico.framework.telehttp.ParamOrigin;
import colesico.framework.telehttp.origin.Origin;
import colesico.framework.telehttp.result.ValueResult;
import colesico.zacepco.docket.srv.dto.CreateInvestigationRequest;
import colesico.zacepco.docket.srv.model.Investigation;
import colesico.zacepco.docket.srv.service.InvestigationService;
import colesico.zacepco.docket.srv.service.ScriptService;
import colesico.zacepco.docket.ui.dto.InvestigationContext;

@Restlet
@Route("./investigation")
public class InvestigationRestlet {

    private final InvestigationService investigationService;
    private final ScriptService scriptService;

    public InvestigationRestlet(InvestigationService investigationService, ScriptService scriptService) {
        this.investigationService = investigationService;
        this.scriptService = scriptService;
    }

    @RequestMethod(HttpMethod.POST)
    @Route("./")
    @Authentication(WebJwt.class)
    public Investigation createInvestigation(@ParamOrigin(Origin.BODY) CreateInvestigationRequest request) {
        return investigationService.createInvestigation(request);
    }

    @Route("./:id")
    public ValueResult<InvestigationContext> findInvestigationContext(@ParamOrigin(Origin.ROUTE) Long id) {
        var invOpt = investigationService.findInvestigationById(id);

        if (invOpt.isPresent()) {
            var inv = invOpt.get();
            var ctx = new InvestigationContext();
            ctx.investigationId = id;
            ctx.state = inv.state;
            ctx.script = scriptService.scriptPackage(inv.scriptId).scriptDocument().read();
            return ValueResult.value(ctx).build();
        } else {
            return ValueResult.empty(InvestigationContext.class).status(204).build();
        }
    }
}
