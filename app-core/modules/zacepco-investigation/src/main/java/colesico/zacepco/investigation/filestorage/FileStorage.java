package colesico.zacepco.investigation.filestorage;

import jakarta.inject.Singleton;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Stream;

@Singleton
public class FileStorage {

    private final FileStorageConfig config;
    private final ConcurrentLinkedQueue<MessageDigest> digestPool;

    public FileStorage(FileStorageConfig config) {
        this.config = config;
        this.digestPool = new ConcurrentLinkedQueue<>();
    }

    private MessageDigest messageDigest() {
        MessageDigest digest = digestPool.poll();
        if (digest == null) {
            try {
                return MessageDigest.getInstance(config.getHashAlgorithm());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Invalid hash algorithm configured: " + config.getHashAlgorithm(), e);
            }
        }
        digest.reset();
        return digest;
    }

    /**
     * Hash from path
     */
    public String toHash(Path path) {
        MessageDigest digest = messageDigest();
        try {
            byte[] hashBytes = digest.digest(path.toString().getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hashBytes);
        } finally {
            // Return digest to pool
            digestPool.offer(digest);
        }
    }

    /**
     * Resolve relative local path to storage full path
     */
    public Path resolve(Path path) {
        Path relativePath = path.isAbsolute() ? path.getRoot().relativize(path) : path;

        var fileHash = toHash(relativePath);
        var bucket1 = fileHash.substring(0, 2);
        var bucket2 = fileHash.substring(2, 4);

        Path storagePath = Paths.get(config.getStorageDirectory()).toAbsolutePath().normalize();
        var fullPath = storagePath.resolve(bucket1).resolve(bucket2).resolve(relativePath).normalize();

        if (!fullPath.startsWith(storagePath)) {
            throw new RuntimeException("Invalid path: " + path);
        }
        return fullPath;
    }

    /**
     * Create directory inside file storage
     *
     * @param path relative local path
     */
    public Path createDirectory(Path path) {
        Path fullPath = resolve(path);
        try {
            Files.createDirectories(fullPath);
            return fullPath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Path writeFile(Path path, InputStream fileData) {
        try {
            Path fullPath = resolve(path);
            Path parentDir = fullPath.getParent();
            if (parentDir != null) {
                Files.createDirectories(parentDir);
            }
            Files.copy(fileData, fullPath, StandardCopyOption.REPLACE_EXISTING);
            return fullPath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFile(Path path, OutputStream output) {
        try {
            Path fullPath = resolve(path);
            if (!Files.exists(fullPath)) {
                throw new RuntimeException("File not found: " + path);
            }
            Files.copy(fullPath, output);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file with ID: " + path, e);
        }
    }

    /**
     * Delete file or directory from file storage
     *
     * @param path relative local path
     */
    public void delete(Path path) {
        try {
            Path fullPath = resolve(path);

            if (Files.deleteIfExists(fullPath)) {
                Path baseStorageDir = Paths.get(config.getStorageDirectory()).toAbsolutePath().normalize();
                Path currentDir = fullPath.toAbsolutePath().normalize();

                while (!currentDir.equals(baseStorageDir)) {
                    if (isDirectoryEmpty(currentDir)) {
                        try {
                            Files.delete(currentDir);
                            currentDir = currentDir.getParent();
                        } catch (DirectoryNotEmptyException e) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete: " + path, e);
        }
    }

    private boolean isDirectoryEmpty(Path directory) throws IOException {
        try (Stream<Path> entries = Files.list(directory)) {
            return entries.findFirst().isEmpty();
        }
    }

    /**
     * Check file or directory exists in file storage
     *
     * @param path relative local path
     */
    public boolean exists(Path path) {
        Path fullPath = resolve(path);
        return Files.exists(fullPath);
    }
}