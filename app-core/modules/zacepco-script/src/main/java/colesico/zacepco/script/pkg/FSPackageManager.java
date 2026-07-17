package colesico.zacepco.script.pkg;

import colesico.framework.ioc.message.IocMessage;
import colesico.framework.ioc.scope.Unscoped;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * File System based package manager.
 * Store package resources in temporary working dir
 */
@Unscoped
public class FSPackageManager extends PackageManager {

    /**
     * Temporary directory where package resource files will be stored
     */
    protected final Path packageDirectory;

    public FSPackageManager(@IocMessage Path packageDirectory) {
        if (packageDirectory != null) {
            this.packageDirectory = packageDirectory;
        } else {
            this.packageDirectory = defaultPackageDirectory();
        }
    }

    protected Path defaultPackageDirectory() {
        try {
            Path systemTemp = Path.of(System.getProperty("java.io.tmpdir"));
            Path subDir = systemTemp.resolve(FSPackageManager.class.getCanonicalName().toLowerCase());
            Files.createDirectories(subDir);
            return Files.createTempDirectory(subDir, "pkg_");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Append working di to relative resource path
     */
    protected Path resolve(ResourcePath path) {
        try {
            var fullPath = packageDirectory.resolve(path.path());
            if (!fullPath.startsWith(packageDirectory)) {
                throw new RuntimeException("Invalid resource path: " + path);
            }
            return fullPath;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Path workingDir() {
        return packageDirectory;
    }

    @Override
    public OutputStream resourceOutput(ResourcePath resourcePath) throws IOException {
        Path path = resolve(resourcePath);
        Path parent = path.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        return Files.newOutputStream(path,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    @Override
    public InputStream resourceInput(ResourcePath resourcePath) throws IOException {
        return Files.newInputStream(resolve(resourcePath));
    }

    @Override
    public void removeResource(ResourcePath resourcePath) throws IOException {
        Files.delete(resolve(resourcePath));
    }

    @Override
    public Collection<ResourcePath> listResources() throws IOException {
        List<ResourcePath> result = new ArrayList<>();
        try (Stream<Path> stream = Files.walk(packageDirectory)) {
            stream.filter(Files::isRegularFile)
                    .map(packageDirectory::relativize)
                    .map(ResourcePath::of)
                    .forEach(result::add);
        }
        return result;
    }

    @Override
    public void close() throws IOException {
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
        }
    }
}
