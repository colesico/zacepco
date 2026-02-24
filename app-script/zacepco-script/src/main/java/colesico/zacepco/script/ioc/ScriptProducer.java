package colesico.zacepco.script.ioc;

import colesico.framework.ioc.production.Produce;
import colesico.framework.ioc.production.Producer;
import colesico.zacepco.script.assist.ScriptWriter;
import colesico.zacepco.script.assist.YamlCustomConstructor;
import colesico.zacepco.script.assist.ScriptReader;
import colesico.zacepco.script.assist.YamlCustomRepresenter;
import colesico.zacepco.script.pkg.FilePackageManager;
import colesico.zacepco.script.pkg.PackageManager;
import colesico.zacepco.script.pkg.ScriptPackage;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

@Producer
@Produce(ScriptReader.class)
@Produce(ScriptWriter.class)
@Produce(value = FilePackageManager.class, keyType = PackageManager.class)
@Produce(ScriptPackage.class)
public class ScriptProducer {

    public Yaml getYaml() {
        Constructor constructor = new YamlCustomConstructor();
        DumperOptions dumperOptions = new DumperOptions();
        Representer representer = new YamlCustomRepresenter(dumperOptions);
        return new Yaml(constructor, representer);
    }
}
