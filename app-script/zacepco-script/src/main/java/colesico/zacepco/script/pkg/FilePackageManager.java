package colesico.zacepco.script.pkg;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class FilePackageManager extends PackageManager {

    protected final Path workingDir;

    protected final Map<ResourcePath, String> resourcesMap = new ConcurrentHashMap<>();

    protected AtomicLong idCounter = new AtomicLong(0);

    public FilePackageManager() {
        try {
            Path systemTemp = Path.of(System.getProperty("java.io.tmpdir"));
            Path subDir = systemTemp.resolve(FilePackageManager.class.getCanonicalName().toLowerCase());
            Files.createDirectories(subDir);
            workingDir = Files.createTempDirectory(subDir, "pkg_");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public FilePackageManager(Path workingDir) {
        this.workingDir = workingDir;
    }

    protected Path resolveResourceFile(String path) {
        return workingDir.resolve(path);
    }

    @Override
    public OutputStream getOutputStream(ResourcePath resourcePath) throws IOException {
        String key = resourcesMap.computeIfAbsent(resourcePath, k -> Long.toString(idCounter.get()));
        Path path = resolveResourceFile(key);
        return Files.newOutputStream(path);
    }

    @Override
    public InputStream getInputStream(ResourcePath resourcePath) throws IOException {
        String key = resourcesMap.get(resourcePath);
        if (key == null) {
            throw new RuntimeException("Unknown resource path: " + resourcePath);
        }
        Path path = resolveResourceFile(key);
        return Files.newInputStream(path);
    }

    @Override
    public void remove(ResourcePath resourcePath) {
        if (resourcesMap.remove(resourcePath) == null) {
            throw new RuntimeException("Unknown resource path: " + resourcePath);
        }
    }

    @Override
    public ResourcePath[] listResources() {
        return resourcesMap.keySet().toArray(ResourcePath[]::new);
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
