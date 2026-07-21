package colesico.zacepco.investigation.srv.ioc;


import colesico.framework.ioc.message.IocMessage;
import colesico.framework.ioc.production.Classed;
import colesico.framework.ioc.production.Produce;
import colesico.framework.ioc.production.Producer;
import colesico.framework.ioc.production.Supplier;
import colesico.framework.ioc.scope.Unscoped;
import colesico.zacepco.investigation.srv.filestorage.StoragePackageDriver;
import colesico.zacepco.script.lib.pkg.PackageDriver;
import colesico.zacepco.script.lib.pkg.ScriptPackage;

@Producer
@Produce(StoragePackageDriver.class)
public class InvestigationProducer {

    @Classed(StoragePackageDriver.class)
    @Unscoped
    public ScriptPackage scriptPackage(
            @IocMessage String packageId,
            Supplier<StoragePackageDriver> packageDriverFactory,
            @Classed(PackageDriver.class) Supplier<ScriptPackage> scriptPackageFactory) {
        return scriptPackageFactory.get(packageDriverFactory.get(packageId));
    }
}
