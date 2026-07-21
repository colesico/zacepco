package colesico.zacepco.investigation.srv.filestorage;

import colesico.framework.ioc.message.IocMessage;
import colesico.framework.ioc.scope.Unscoped;
import colesico.zacepco.script.lib.pkg.PackageDriver;
import colesico.zacepco.script.lib.pkg.ResourcePath;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * File storage based package driver.
 * Stores package resources in FileStorage with hash-based directory structure.
 */
@Unscoped
public class StoragePackageDriver extends PackageDriver {

    private final String packageId;
    private final FileStorage fileStorage;

    public StoragePackageDriver(@IocMessage String packageId,
                                FileStorage fileStorage) {

        this.fileStorage = fileStorage;
        this.packageId = packageId;
    }

    /**
     * Convert ResourcePath to Path for FileStorage.
     * All resources are stored under packageId directory to avoid collisions between packages.
     *
     * @param resourcePath the resource path
     * @return Path for FileStorage
     */
    protected Path resolve(ResourcePath resourcePath) {
        return Path.of(packageId).resolve(resourcePath.path());
    }

    @Override
    public OutputStream resourceOutput(ResourcePath resourcePath) throws IOException {
        Path storagePath = resolve(resourcePath);
        return fileStorage.fileOutput(storagePath);
    }

    @Override
    public InputStream resourceInput(ResourcePath resourcePath) throws IOException {
        Path storagePath = resolve(resourcePath);
        return fileStorage.fileInput(storagePath);
    }

    @Override
    public void removeResource(ResourcePath resourcePath) throws IOException {
        Path storagePath = resolve(resourcePath);
        fileStorage.delete(storagePath);
    }

    @Override
    public Collection<ResourcePath> listResources() throws IOException {
        Path packageRoot = Path.of(packageId);

        try (Stream<Path> stream = fileStorage.list(packageRoot, Files::isRegularFile)) {
            return stream
                    .map(ResourcePath::of)
                    .toList();
        }
    }

    @Override
    public void close() throws IOException {
        // Nothing to close, FileStorage is managed by DI
    }
}