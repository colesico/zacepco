package colesico.zacepco.casereg.srv.jdbi;

import colesico.framework.jdbirec.mediators.EnumTextMediator;
import colesico.zacepco.casereg.srv.model.CasebookAccessType;

public class AccessTypeMediator extends EnumTextMediator<CasebookAccessType> {

    @Override
    protected CasebookAccessType valueOf(String name) {
        return CasebookAccessType.valueOf(name);
    }

}
