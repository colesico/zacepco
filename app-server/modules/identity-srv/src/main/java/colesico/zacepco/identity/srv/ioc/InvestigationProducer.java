package colesico.zacepco.identity.srv.ioc;


import colesico.framework.ioc.production.Produce;
import colesico.framework.ioc.production.Producer;
import colesico.zacepco.identity.srv.v8n.UserValidatorBuilder;

@Producer
@Produce(UserValidatorBuilder.class)
public class InvestigationProducer {

    public Long get(){
        return null;
    }

}
