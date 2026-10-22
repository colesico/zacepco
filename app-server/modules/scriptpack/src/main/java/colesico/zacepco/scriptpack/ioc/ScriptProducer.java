package colesico.zacepco.scriptpack.ioc;

import colesico.framework.ioc.message.IocMessage;
import colesico.framework.ioc.production.Classed;
import colesico.framework.ioc.production.Produce;
import colesico.framework.ioc.production.Producer;
import colesico.framework.ioc.production.Supplier;
import colesico.framework.ioc.scope.Unscoped;
import colesico.zacepco.scriptpack.model.script.ScriptDocument;
import colesico.zacepco.scriptpack.pkg.*;
import colesico.zacepco.scriptpack.yaml.YamlCustomConstructor;
import colesico.zacepco.scriptpack.yaml.YamlCustomRepresenter;
import colesico.zacepco.scriptpack.pkg.DirectoryPackageDriver;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.nio.file.Path;

@Producer
@Produce(YamlReader.class)
@Produce(YamlWriter.class)
@Produce(DirectoryPackageDriver.class)
@Produce(value = ScriptPackage.class, classed = PackageDriver.class)
public class ScriptProducer {

    @Unscoped
    public Yaml yaml() {
        Constructor constructor = new YamlCustomConstructor();
        constructor.getPropertyUtils().setBeanAccess(BeanAccess.PROPERTY);

        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        dumperOptions.setPrettyFlow(false);
        dumperOptions.setIndent(2);
        dumperOptions.setProcessComments(true);
        dumperOptions.setAllowUnicode(true);
        dumperOptions.setSplitLines(false);
        dumperOptions.setWidth(Integer.MAX_VALUE);

        Representer representer = new YamlCustomRepresenter(dumperOptions);
        representer.addClassTag(ScriptDocument.class, Tag.MAP);

        return new Yaml(constructor, representer, dumperOptions);
    }

    // Default script package
    @Unscoped
    public ScriptPackage scriptPackageDefault(
            @Classed(PackageDriver.class) Supplier<ScriptPackage> scriptPackageFactory,
            DirectoryPackageDriver packageDriver) {
        return scriptPackageFactory.get(packageDriver);
    }

    /**
     *  To get ScriptPackage instance with DirectoryPackageDriver for specified path
     */
    @Unscoped
    @Classed(DirectoryPackageDriver.class)
    public ScriptPackage scriptPackageForScriptDirectory(
            @Classed(PackageDriver.class) Supplier<ScriptPackage> scriptPackageFactory,
            Supplier<DirectoryPackageDriver> packageDriverFactory,
            @IocMessage Path scriptDir) {
        return scriptPackageFactory.get(packageDriverFactory.get(scriptDir));
    }
}
