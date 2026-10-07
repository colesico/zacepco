package colesico.zacepco.cases.srv.jdbi;

import colesico.framework.jdbirec.mediators.EnumTextMediator;
import colesico.zacepco.cases.srv.model.CaseFileAccessType;

public class AccessTypeMediator extends EnumTextMediator<CaseFileAccessType> {

    @Override
    protected CaseFileAccessType valueOf(String name) {
        return CaseFileAccessType.valueOf(name);
    }

}
