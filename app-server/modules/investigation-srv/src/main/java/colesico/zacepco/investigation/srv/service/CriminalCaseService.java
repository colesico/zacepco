package colesico.zacepco.investigation.srv.service;

import colesico.framework.service.Service;
import colesico.zacepco.investigation.srv.model.InvestigationProcess;
import colesico.zacepco.investigation.srv.model.InvestigationStatus;

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
