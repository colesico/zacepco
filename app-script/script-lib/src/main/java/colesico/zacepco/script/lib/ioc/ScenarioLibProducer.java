package colesico.zacepco.script.lib.ioc;

import colesico.framework.ioc.production.Produce;
import colesico.framework.ioc.production.Producer;
import colesico.zacepco.script.lib.assist.ScriptIO;
import org.yaml.snakeyaml.Yaml;

@Producer
@Produce(Yaml.class)
@Produce(ScriptIO.class)
public class ScenarioLibProducer {
}
