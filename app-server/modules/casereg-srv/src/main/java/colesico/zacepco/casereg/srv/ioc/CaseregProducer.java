package colesico.zacepco.casereg.srv.ioc;


import colesico.framework.ioc.message.IocMessage;
import colesico.framework.ioc.production.Classed;
import colesico.framework.ioc.production.Produce;
import colesico.framework.ioc.production.Producer;
import colesico.framework.ioc.production.Supplier;
import colesico.framework.ioc.scope.Unscoped;
import colesico.zacepco.casereg.srv.scriptpack.FileStoragePackageDriver;
import colesico.zacepco.scriptpack.pkg.PackageDriver;
import colesico.zacepco.scriptpack.pkg.ScriptPackage;

@Producer
@Produce(FileStoragePackageDriver.class)
public class CaseregProducer {

    @Classed(FileStoragePackageDriver.class)
    @Unscoped
    public ScriptPackage scriptPackage(
            @IocMessage String packageId,
            Supplier<FileStoragePackageDriver> packageDriverFactory,
            @Classed(PackageDriver.class) Supplier<ScriptPackage> scriptPackageFactory) {
        return scriptPackageFactory.get(packageDriverFactory.get(packageId));
    }
}
