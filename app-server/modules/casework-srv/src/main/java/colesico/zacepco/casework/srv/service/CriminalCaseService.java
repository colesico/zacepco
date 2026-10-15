package colesico.zacepco.casework.srv.service;

import colesico.framework.service.Service;
import colesico.zacepco.casework.srv.model.InvestigationProcess;
import colesico.zacepco.casework.srv.model.InvestigationStatus;

@Service
public class CriminalCaseService {

    public InvestigationProcess createCase(Long scriptId){
        InvestigationProcess inv = new InvestigationProcess();
        inv.setStatus(InvestigationStatus.NEW);
        return inv;
    }

    public void openCase(Long caseId){

    }

    public void closeCase(Long caseId){

    }

}
