package colesico.zacepco.script.ioc;

import colesico.framework.ioc.production.Produce;
import colesico.framework.ioc.production.Producer;
import colesico.zacepco.script.pkg.ScriptWriter;
import colesico.zacepco.script.pkg.YamlCustomConstructor;
import colesico.zacepco.script.pkg.ScriptReader;
import colesico.zacepco.script.pkg.YamlCustomRepresenter;
import colesico.zacepco.script.pkg.DefaultPackageManager;
import colesico.zacepco.script.pkg.PackageManager;
import colesico.zacepco.script.pkg.ScriptPackage;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

@Producer
@Produce(ScriptReader.class)
@Produce(ScriptWriter.class)
@Produce(value = DefaultPackageManager.class, keyType = PackageManager.class)
@Produce(ScriptPackage.class)
public class ScriptProducer {

    public Yaml getYaml() {
        Constructor constructor = new YamlCustomConstructor();
        DumperOptions dumperOptions = new DumperOptions();
        Representer representer = new YamlCustomRepresenter(dumperOptions);
        return new Yaml(constructor, representer);
    }
}
