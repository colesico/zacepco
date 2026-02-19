package colesico.zacepco.script.spec.ioc;

import colesico.framework.ioc.production.Produce;
import colesico.framework.ioc.production.Producer;
import colesico.zacepco.script.spec.assist.ScriptSerializer;
import org.yaml.snakeyaml.Yaml;

@Producer
@Produce(Yaml.class)
@Produce(ScriptSerializer.class)
public class ScriptProducer {
}
