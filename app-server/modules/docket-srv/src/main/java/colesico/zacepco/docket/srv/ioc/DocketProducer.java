package colesico.zacepco.docket.srv.ioc;


import colesico.framework.ioc.message.IocMessage;
import colesico.framework.ioc.production.Classed;
import colesico.framework.ioc.production.Produce;
import colesico.framework.ioc.production.Producer;
import colesico.framework.ioc.production.Supplier;
import colesico.framework.ioc.scope.Unscoped;
import colesico.zacepco.docket.srv.scriptext.FileStoragePackageDriver;
import colesico.zacepco.script.pkg.PackageDriver;
import colesico.zacepco.script.pkg.ScriptPackage;

@Producer
@Produce(FileStoragePackageDriver.class)
public class DocketProducer {

    @Classed(FileStoragePackageDriver.class)
    @Unscoped
    public ScriptPackage scriptPackage(
            @IocMessage String packageId,
            Supplier<FileStoragePackageDriver> packageDriverFactory,
            @Classed(PackageDriver.class) Supplier<ScriptPackage> scriptPackageFactory) {
        return scriptPackageFactory.get(packageDriverFactory.get(packageId));
    }
}
