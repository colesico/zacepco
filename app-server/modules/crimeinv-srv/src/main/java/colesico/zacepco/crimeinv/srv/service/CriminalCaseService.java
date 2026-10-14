package colesico.zacepco.crimeinv.srv.service;

import colesico.framework.service.Service;
import colesico.zacepco.crimeinv.srv.model.InvestigationProcess;
import colesico.zacepco.crimeinv.srv.model.InvestigationStatus;

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
