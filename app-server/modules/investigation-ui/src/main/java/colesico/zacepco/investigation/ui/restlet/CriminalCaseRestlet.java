package colesico.zacepco.investigation.ui.restlet;

import colesico.framework.httprouter.Route;
import colesico.framework.restlet.Restlet;
import colesico.zacepco.crimeinv.srv.model.InvestigationProcess;

import java.util.List;

@Restlet
@Route("./criminal-case")
public class CriminalCaseRestlet {

    public List<InvestigationProcess> lastCases(Integer limit, Long offset){
        return List.of(new InvestigationProcess());
    }

}
