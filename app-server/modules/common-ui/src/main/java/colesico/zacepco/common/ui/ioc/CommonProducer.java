package colesico.zacepco.common.ui.ioc;

import colesico.framework.ioc.production.Classed;
import colesico.framework.ioc.production.Produce;
import colesico.framework.ioc.production.Producer;
import colesico.framework.restlet.RestletWriter;
import colesico.zacepco.common.ui.teleapi.CustomJsonExceptionWriter;
import jakarta.inject.Singleton;

@Producer
@Produce(CustomJsonExceptionWriter.class)
public class CommonProducer {

    @Singleton
    @Classed(Exception.class)
    public RestletWriter exceptionWriter(CustomJsonExceptionWriter impl) {
        return impl;
    }
}
