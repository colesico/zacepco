package colesico.zacepco.inquiry.srv.service;

import colesico.framework.service.Service;
import colesico.zacepco.inquiry.srv.model.Investigation;
import colesico.zacepco.inquiry.srv.model.InvestigationStatus;

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
