package colesico.zacepco.investigation.ui.restlet;

import colesico.framework.httprouter.Route;
import colesico.framework.restlet.Restlet;
import colesico.zacepco.investigation.srv.model.CriminalCase;

import java.util.List;

@Restlet
@Route("./criminal-case")
public class CriminalCaseRestlet {

    public List<CriminalCase> lastCases(Integer limit, Long offset){
        return List.of(new CriminalCase());
    }

}
