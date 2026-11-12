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

@Restlet
@Route("./investigation")
public class InvestigationRestlet {

    private final InvestigationService investigationService;

    public InvestigationRestlet(InvestigationService investigationService) {
        this.investigationService = investigationService;
    }

    @RequestMethod(HttpMethod.POST)
    @Route("./")
    @Authentication(WebJwt.class)
    public Investigation createInvestigationEntry(@ParamOrigin(Origin.BODY) CreateInvestigationRequest request) {
        return investigationService.createInvestigation(request);
    }

    @Route("./:id")
    public ValueResult<Investigation> findInvestigationEntry(@ParamOrigin(Origin.ROUTE) Long id) {
        var optEntry = investigationService.findInvestigationById(id);

        if (optEntry.isPresent()) {
            return ValueResult.value(optEntry.get()).build();
        } else {
            return ValueResult.empty(Investigation.class).status(204).build();
        }
    }
}
