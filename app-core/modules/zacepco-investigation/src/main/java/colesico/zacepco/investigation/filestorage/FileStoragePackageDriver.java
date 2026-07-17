package colesico.zacepco.investigation.filestorage;

import colesico.framework.ioc.message.IocMessage;
import colesico.framework.ioc.production.Supplier;
import colesico.zacepco.script.pkg.FSPackageDriver;
import colesico.zacepco.script.pkg.PackageDriver;
import colesico.zacepco.script.pkg.ResourcePath;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

public class FileStoragePackageDriver extends PackageDriver {

    private final FSPackageDriver packageDriver;
    private final FileStorage fileStorage;

    public FileStoragePackageDriver(
            FileStorage fileStorage,
            @IocMessage String packageId,
            Supplier<FSPackageDriver> packageDriver) {

        fileStorage
        this.packageDriver = packageDriver;
    }

    @Override
    public OutputStream resourceOutput(ResourcePath resourcePath) throws IOException {
        return null;
    }

    @Override
    public InputStream resourceInput(ResourcePath resourcePath) throws IOException {
        return null;
    }

    @Override
    public void removeResource(ResourcePath resourcePath) throws IOException {

    }

    @Override
    public Collection<ResourcePath> listResources() throws IOException {
        return List.of();
    }

    @Override
    public void close() throws IOException {

    }
}
