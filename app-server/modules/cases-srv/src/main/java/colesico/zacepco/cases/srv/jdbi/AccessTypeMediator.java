package colesico.zacepco.cases.srv.jdbi;

import colesico.framework.jdbirec.mediators.EnumMediator;
import colesico.zacepco.cases.srv.model.CaseFileAccessType;

public class AccessTypeMediator extends EnumMediator<CaseFileAccessType> {

    @Override
    protected CaseFileAccessType valueOf(String name) {
        return CaseFileAccessType.valueOf(name);
    }

}
