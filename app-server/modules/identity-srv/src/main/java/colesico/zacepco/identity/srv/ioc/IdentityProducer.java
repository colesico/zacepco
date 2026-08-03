package colesico.zacepco.identity.srv.ioc;


import colesico.framework.ioc.production.Produce;
import colesico.framework.ioc.production.Producer;
import colesico.zacepco.identity.srv.validation.RegValidatorBuilder;
import colesico.zacepco.identity.srv.validation.UserValidatorBuilder;

@Producer
@Produce(UserValidatorBuilder.class)
@Produce(RegValidatorBuilder.class)
public class IdentityProducer {

    public Long get(){
        return null;
    }

}
