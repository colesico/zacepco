package colesico.zacepco.script.pkg;

import colesico.framework.ioc.message.IocMessage;
import colesico.framework.ioc.scope.Unscoped;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Temporary directory based package manager.
 */
@Unscoped
public class DirectoryPackageDriver extends PackageDriver {

    /**
     * Temporary directory where package resource files will be stored
     */
    protected final Path packageDirectory;

    public DirectoryPackageDriver(@IocMessage Path packageDirectory) {
        if (packageDirectory != null) {
            this.packageDirectory = packageDirectory;
        } else {
            this.packageDirectory = defaultPackageDirectory();
        }
    }

    protected Path defaultPackageDirectory() {
        try {
            Path systemTemp = Path.of(System.getProperty("java.io.tmpdir"));
            Path subDir = systemTemp.resolve(DirectoryPackageDriver.class.getCanonicalName().toLowerCase());
            Files.createDirectories(subDir);
            return Files.createTempDirectory(subDir, "pkg_");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Append working di to relative resource path
     */
    protected Path resolve(ResourcePath rp) {
        var fullPath = packageDirectory.resolve(rp.asPath());
        if (!fullPath.startsWith(packageDirectory)) {
            throw new RuntimeException("Invalid resource path: " + rp);
        }
        return fullPath;
    }

    public Path workingDir() {
        return packageDirectory;
    }

    @Override
    public OutputStream resourceOutput(ResourcePath resourcePath) {
        try {
            Path path = resolve(resourcePath);
            Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            return Files.newOutputStream(path,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    @Override
    public InputStream resourceInput(ResourcePath resourcePath) {
        try {
            return Files.newInputStream(resolve(resourcePath));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void removeResource(ResourcePath resourcePath) {
        try {
            Files.delete(resolve(resourcePath));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public Collection<ResourcePath> listResources() {
        List<ResourcePath> result = new ArrayList<>();
        try (Stream<Path> stream = Files.walk(packageDirectory)) {
            stream.filter(Files::isRegularFile)
                    .map(packageDirectory::relativize)
                    .map(ResourcePath::of)
                    .forEach(result::add);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return result;
    }

    /**
     * Remove entire packageDirectory
     */
    @Override
    public void close() {
        if (!Files.exists(packageDirectory)) return;

        try (var stream = Files.walk(packageDirectory)) {
            stream.sorted(Comparator.reverseOrder()) // Remove dir content, then dir itself
                    .forEach(p -> {
                        try {
                            Files.delete(p);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }
}
