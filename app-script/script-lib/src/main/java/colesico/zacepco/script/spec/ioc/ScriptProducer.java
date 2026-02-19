package colesico.zacepco.script.spec.ioc;

import colesico.framework.ioc.production.Produce;
import colesico.framework.ioc.production.Producer;
import colesico.zacepco.script.spec.assist.YamlCustomConstructor;
import colesico.zacepco.script.spec.assist.ScriptReader;
import org.yaml.snakeyaml.Yaml;

@Producer
@Produce(ScriptReader.class)
public class ScriptProducer {

    public Yaml getYaml(){
       return new Yaml(new YamlCustomConstructor());
        //return new Yaml();
    }
}
