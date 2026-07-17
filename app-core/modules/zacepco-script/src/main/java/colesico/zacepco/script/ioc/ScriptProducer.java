package colesico.zacepco.script.ioc;

import colesico.framework.ioc.message.IocMessage;
import colesico.framework.ioc.production.Classed;
import colesico.framework.ioc.production.Produce;
import colesico.framework.ioc.production.Producer;
import colesico.framework.ioc.production.Supplier;
import colesico.framework.ioc.scope.Unscoped;
import colesico.zacepco.script.model.script.Script;
import colesico.zacepco.script.pkg.*;
import colesico.zacepco.script.yaml.YamlCustomConstructor;
import colesico.zacepco.script.yaml.YamlCustomRepresenter;
import colesico.zacepco.script.pkg.FSPackageManager;
import jakarta.inject.Provider;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.nio.file.Path;

@Producer
@Produce(ScriptReader.class)
@Produce(ScriptWriter.class)
@Produce(FSPackageManager.class)
@Produce(ScriptPackage.class)
public class ScriptProducer {

    @Unscoped
    public Yaml yaml() {
        Constructor constructor = new YamlCustomConstructor();

        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        dumperOptions.setPrettyFlow(true);
        dumperOptions.setIndent(2);
        dumperOptions.setProcessComments(true);

        Representer representer = new YamlCustomRepresenter(dumperOptions);
        representer.addClassTag(Script.class, Tag.MAP);

        return new Yaml(constructor, representer, dumperOptions);
    }

    @Unscoped
    public PackageManager packageManager(@IocMessage Path packageDirectory,
                                         Supplier<FSPackageManager> fsPackageManager) {
        return fsPackageManager.get(packageDirectory);
    }

}
