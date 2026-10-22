package colesico.zacepco.casereg.srv.ioc;


import colesico.framework.ioc.message.IocMessage;
import colesico.framework.ioc.production.Classed;
import colesico.framework.ioc.production.Produce;
import colesico.framework.ioc.production.Producer;
import colesico.framework.ioc.production.Supplier;
import colesico.framework.ioc.scope.Unscoped;
import colesico.zacepco.casereg.srv.filestorage.StoragePackageDriver;
import colesico.zacepco.scriptpack.pkg.PackageDriver;
import colesico.zacepco.scriptpack.pkg.ResourcePackage;

@Producer
@Produce(StoragePackageDriver.class)
public class CaseregProducer {

    @Classed(StoragePackageDriver.class)
    @Unscoped
    public ResourcePackage scriptPackage(
            @IocMessage String packageId,
            Supplier<StoragePackageDriver> packageDriverFactory,
            @Classed(PackageDriver.class) Supplier<ResourcePackage> scriptPackageFactory) {
        return scriptPackageFactory.get(packageDriverFactory.get(packageId));
    }
}
