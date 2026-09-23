package colesico.zacepco.common.ui.ioc;

import colesico.framework.ioc.production.Produce;
import colesico.framework.ioc.production.Producer;
import colesico.zacepco.common.ui.teleapi.CustomJsonExceptionWriter;

@Producer
@Produce(CustomJsonExceptionWriter.class)
public class CommonProducer {
}
