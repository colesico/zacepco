package colesico.zacepco.investigation.service;

import colesico.framework.service.Service;
import colesico.zacepco.investigation.model.Investigation;
import colesico.zacepco.investigation.model.InvestigationStatus;

@Service
public class InvestigationService {

    public Investigation createInvestigation(Long scriptId){
        Investigation inv = new Investigation();
        inv.status = InvestigationStatus.NEW;
        return inv;
    }

    public void startInvestigation(Long investigationId){

    }

    public void stopInvestigation(Long investigationId){

    }

}
