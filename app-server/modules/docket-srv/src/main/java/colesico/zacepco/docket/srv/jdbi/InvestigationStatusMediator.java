package colesico.zacepco.docket.srv.jdbi;

import colesico.framework.jdbirec.mediators.EnumTextMediator;
import colesico.zacepco.docket.srv.model.InvestigationStatus;

public class InvestigationStatusMediator extends EnumTextMediator<InvestigationStatus> {

    @Override
    protected InvestigationStatus valueOf(String name) {
        return InvestigationStatus.valueOf(name);
    }

}
