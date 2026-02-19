package colesico.zacepco.script.spec.ioc;

import colesico.framework.ioc.production.Produce;
import colesico.framework.ioc.production.Producer;
import colesico.zacepco.script.spec.assist.ScriptWriter;
import colesico.zacepco.script.spec.assist.YamlCustomConstructor;
import colesico.zacepco.script.spec.assist.ScriptReader;
import colesico.zacepco.script.spec.assist.YamlCustomRepresenter;
import colesico.zacepco.script.spec.model.entity.Personage;
import colesico.zacepco.script.spec.model.entity.PersonageId;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.introspector.PropertyUtils;
import org.yaml.snakeyaml.representer.Representer;

@Producer
@Produce(ScriptReader.class)
@Produce(ScriptWriter.class)
public class ScriptProducer {

    public Yaml getYaml() {
        Constructor constructor = new YamlCustomConstructor();
        DumperOptions dumperOptions  = new DumperOptions();
        Representer representer = new YamlCustomRepresenter(dumperOptions);
        return new Yaml(constructor,representer);
    }
}
