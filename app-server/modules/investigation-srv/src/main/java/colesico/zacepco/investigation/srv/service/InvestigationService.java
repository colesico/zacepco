package colesico.zacepco.investigation.srv.service;

import colesico.framework.service.Service;
import colesico.zacepco.investigation.srv.model.Investigation;
import colesico.zacepco.investigation.srv.model.InvestigationStatus;

@Service
public class InvestigationService {

    public Investigation create(Long scriptId){
        Investigation inv = new Investigation();
        inv.setStatus(InvestigationStatus.NEW);
        return inv;
    }

    public void start(Long investigationId){

    }

    public void stop(Long investigationId){

    }

}
