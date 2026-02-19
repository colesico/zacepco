package colesico.zacepco.script.spec.ioc;

import colesico.framework.ioc.production.Produce;
import colesico.framework.ioc.production.Producer;
import colesico.zacepco.script.spec.assist.YamlCustomConstructor;
import colesico.zacepco.script.spec.assist.ScriptReader;
import colesico.zacepco.script.spec.model.entity.Personage;
import colesico.zacepco.script.spec.model.entity.PersonageId;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

@Producer
@Produce(ScriptReader.class)
public class ScriptProducer {

    public Yaml getYaml() {
        Constructor constructor = new YamlCustomConstructor();
        return new Yaml(constructor);
    }
}
