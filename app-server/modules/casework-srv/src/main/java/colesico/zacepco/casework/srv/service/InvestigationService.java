package colesico.zacepco.casework.srv.service;

import colesico.framework.service.Service;
import colesico.zacepco.casework.srv.model.InvestigationEntry;
import colesico.zacepco.casework.srv.model.InvestigationStatus;

@Service
public class InvestigationService {

    public InvestigationEntry createCase(Long scriptId){
        InvestigationEntry inv = new InvestigationEntry();
        inv.setStatus(InvestigationStatus.OPEN);
        return inv;
    }

    public void openCase(Long caseId){

    }

    public void closeCase(Long caseId){

    }

}
