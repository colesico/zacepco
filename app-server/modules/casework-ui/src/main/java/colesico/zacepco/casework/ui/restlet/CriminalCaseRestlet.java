package colesico.zacepco.casework.ui.restlet;

import colesico.framework.httprouter.Route;
import colesico.framework.restlet.Restlet;
import colesico.zacepco.casework.srv.model.InvestigationEntry;

import java.util.List;

@Restlet
@Route("./criminal-case")
public class CriminalCaseRestlet {

    public List<InvestigationEntry> lastCases(Integer limit, Long offset){
        return List.of(new InvestigationEntry());
    }

}
