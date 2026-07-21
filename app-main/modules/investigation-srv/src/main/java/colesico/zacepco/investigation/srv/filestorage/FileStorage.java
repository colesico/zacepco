package colesico.zacepco.investigation.srv.filestorage;

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

        Path storagePath = config.storagePath();
        var fullPath = storagePath.resolve(bucket1).resolve(bucket2).resolve(relativePath).normalize();

        if (!fullPath.startsWith(storagePath)) {
            throw new RuntimeException("Invalid path: " + path);
        }
        return fullPath;
    }


    /**
     * Opens an output stream for writing data to the specified file in the storage.
     * Creates parent directories if they don't exist.
     * If the file already exists, it will be truncated and overwritten.
     * The caller is responsible for closing the returned stream.
     *
     * @param filePath relative local path to the file
     * @return OutputStream for writing data to the file
     * @throws RuntimeException if an I/O error occurs
     */
    public OutputStream fileOutput(Path filePath) {
        try {
            Path fullPath = resolve(filePath);
            Path parentDir = fullPath.getParent();
            if (parentDir != null) {
                Files.createDirectories(parentDir);
            }
            return Files.newOutputStream(fullPath,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to open output stream for: " + filePath, e);
        }
    }

    /**
     * Opens an input stream for reading data from the specified file in the storage.
     * The caller is responsible for closing the returned stream.
     *
     * @param filePath relative local path to the file
     * @return InputStream for reading data from the file
     * @throws RuntimeException if the file does not exist or an I/O error occurs
     */
    public InputStream fileInput(Path filePath) {
        try {
            Path fullPath = resolve(filePath);
            if (!Files.exists(fullPath)) {
                throw new RuntimeException("File not found: " + filePath);
            }
            return Files.newInputStream(fullPath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to open input stream for: " + filePath, e);
        }
    }

    /**
     * Create directory inside file storage
     *
     * @param dirPath relative local path
     */
    public Path createDirectory(Path dirPath) {
        Path fullPath = resolve(dirPath);
        try {
            Files.createDirectories(fullPath);
            return fullPath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * List all files and directories recursively under the given path
     *
     * @param path   relative local path
     * @param filter predicate to filter entries (e.g., Files::isRegularFile for files only)
     * @return Stream of Paths (relative to given path)
     */
    public Stream<Path> list(Path path, java.util.function.Predicate<Path> filter) {
        try {
            Path fullPath = resolve(path);
            if (!Files.exists(fullPath)) {
                return Stream.empty();
            }
            return Files.walk(fullPath)
                    .filter(p -> !p.equals(fullPath)) // исключаем саму директорию
                    .filter(filter)
                    .map(fullPath::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Failed to list: " + path, e);
        }
    }

    /**
     * List all files and directories recursively under the given path
     *
     * @param path relative local path
     * @return Stream of Paths (relative to given path)
     */
    public Stream<Path> list(Path path) {
        return list(path, p -> true);
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

    /**
     * Delete file or empty directory from file storage
     * Also deletes empty hash-based index directories (buckets) if they become empty
     *
     * @param path relative local path
     */
    public void delete(Path path) {
        try {
            Path fullPath = resolve(path);
            Path storagePath = config.storagePath();

            // Prevent deletion of the storage root directory
            if (fullPath.equals(storagePath)) {
                throw new RuntimeException("Cannot delete storage root directory");
            }

            // Try to delete the file or directory
            // Returns true if file existed and was deleted, false if it didn't exist
            boolean deleted = Files.deleteIfExists(fullPath);

            if (deleted) {
                // Only clean up empty parent directories if we actually deleted something
                Path currentDir = fullPath.getParent();

                while (currentDir != null && !currentDir.equals(storagePath)) {
                    if (isDirectoryEmpty(currentDir)) {
                        try {
                            Files.deleteIfExists(currentDir);
                            currentDir = currentDir.getParent();
                        } catch (DirectoryNotEmptyException e) {
                            // Directory became non-empty during deletion (concurrent modification)
                            break;
                        }
                    } else {
                        // Directory is not empty, stop cleaning
                        break;
                    }
                }
            }
            // If file didn't exist, we do nothing (idempotent operation)

        } catch (IOException e) {
            throw new RuntimeException("Failed to delete: " + path, e);
        }
    }

    protected boolean isDirectoryEmpty(Path path) throws IOException {
        try (Stream<Path> entries = Files.list(path)) {
            return entries.findFirst().isEmpty();
        }
    }

}