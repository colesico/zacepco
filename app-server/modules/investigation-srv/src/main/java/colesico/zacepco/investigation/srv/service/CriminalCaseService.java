package colesico.zacepco.investigation.srv.service;

import colesico.framework.service.Service;
import colesico.zacepco.investigation.srv.model.CriminalCase;
import colesico.zacepco.investigation.srv.model.CriminalCaseStatus;

@Service
public class CriminalCaseService {

    public CriminalCase createCase(Long scriptId){
        CriminalCase inv = new CriminalCase();
        inv.setStatus(CriminalCaseStatus.NEW);
        return inv;
    }

    public void openCase(Long caseId){

    }

    public void closeCase(Long caseId){

    }

}
