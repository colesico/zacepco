package colesico.zacepco.script.pkg;

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

public class DefaultPackageManager extends PackageManager {

    protected final Path workingDir;

    public DefaultPackageManager() {
        workingDir = defaultWorkingDir();
    }

    public DefaultPackageManager(Path workingDir) {
        this.workingDir = workingDir;
    }

    protected Path defaultWorkingDir() {
        try {
            Path systemTemp = Path.of(System.getProperty("java.io.tmpdir"));
            Path subDir = systemTemp.resolve(DefaultPackageManager.class.getCanonicalName().toLowerCase());
            Files.createDirectories(subDir);
            return Files.createTempDirectory(subDir, "pkg_");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Append working di to relative resource path
     */
    protected Path resolveResourcePath(ResourcePath path) {
        try {
            var fullPath = workingDir.resolve(path.path());
            if (!fullPath.startsWith(workingDir)) {
                throw new RuntimeException("Invalid resource path: " + path);
            }
            return fullPath;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OutputStream getOutputStream(ResourcePath resourcePath) throws IOException {
        Path path = resolveResourcePath(resourcePath);
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
    public InputStream getInputStream(ResourcePath resourcePath) throws IOException {
        return Files.newInputStream(resolveResourcePath(resourcePath));
    }

    @Override
    public void remove(ResourcePath resourcePath) throws IOException {
        Files.delete(resolveResourcePath(resourcePath));
    }

    @Override
    public Collection<ResourcePath> listResources() throws IOException {
        List<ResourcePath> result = new ArrayList<>();
        try (Stream<Path> stream = Files.walk(workingDir)) {
            stream.filter(Files::isRegularFile)
                    .map(workingDir::relativize)
                    .map(ResourcePath::of)
                    .forEach(result::add);
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        if (!Files.exists(workingDir)) return;

        try (var stream = Files.walk(workingDir)) {
            stream.sorted(Comparator.reverseOrder()) // Сначала содержимое, потом сама папка
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
