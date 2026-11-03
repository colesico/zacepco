package colesico.zacepco.docket.ui.restlet;

import colesico.framework.http.HttpMethod;
import colesico.framework.httprouter.RequestMethod;
import colesico.framework.httprouter.Route;
import colesico.framework.jjwt.WebJwt;
import colesico.framework.restlet.Restlet;
import colesico.framework.security.authentication.Authentication;
import colesico.framework.telehttp.ParamName;
import colesico.zacepco.docket.srv.model.InvestigationEntry;
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
    public InvestigationEntry createInvestigationEntry(@ParamName("scriptEntry") Long scriptEntryId) {
        return investigationService.createInvestigationEntry(scriptEntryId);
    }

}
