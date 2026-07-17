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

    private MessageDigest getMessageDigest() {
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

    private String toSafeFileId(String fileId) {
        if (fileId == null || fileId.isBlank()) {
            throw new IllegalArgumentException("File ID cannot be null or empty");
        }
        return Paths.get(fileId).getFileName().toString();
    }

    public String toHash(String fileId) {
        MessageDigest digest = getMessageDigest();
        try {
            byte[] hashBytes = digest.digest(fileId.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hashBytes);
        } finally {
            // Return digest to pool
            digestPool.offer(digest);
        }
    }

    public Path targetDirectory(String fileId) {
        var fileHash = toHash(fileId);
        String key1 = fileHash.substring(0, 2);
        String key2 = fileHash.substring(2, 4);
        Path storageDir = Paths.get(config.getStorageDirectory());
        return storageDir.resolve(key1).resolve(key2);
    }

    public Path save(String fileId, InputStream fileData) {
        try {
            String safeFileId = toSafeFileId(fileId);
            Path targetDirectory = targetDirectory(safeFileId);
            Files.createDirectories(targetDirectory);
            Path targetFilePath = targetDirectory.resolve(safeFileId);
            Files.copy(fileData, targetFilePath, StandardCopyOption.REPLACE_EXISTING);
            return targetFilePath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void read(String fileId, OutputStream output) {
        try {
            String safeFileId = toSafeFileId(fileId);
            Path targetFilePath = targetDirectory(safeFileId).resolve(safeFileId);
            if (!Files.exists(targetFilePath)) {
                throw new RuntimeException("File not found with ID: " + fileId);
            }
            Files.copy(targetFilePath, output);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file with ID: " + fileId, e);
        }
    }

    public void delete(String fileId) {
        try {
            String safeFileId = toSafeFileId(fileId);
            Path targetDirectory = targetDirectory(safeFileId);
            Path targetFilePath = targetDirectory.resolve(safeFileId);

            if (Files.deleteIfExists(targetFilePath)) {
                Path baseStorageDir = Paths.get(config.getStorageDirectory()).toAbsolutePath().normalize();
                Path currentDir = targetDirectory.toAbsolutePath().normalize();

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
            throw new RuntimeException("Failed to delete file with ID: " + fileId, e);
        }
    }

    private boolean isDirectoryEmpty(Path directory) throws IOException {
        try (Stream<Path> entries = Files.list(directory)) {
            return entries.findFirst().isEmpty();
        }
    }

    public boolean exists(String fileId) {
        String safeFileId = toSafeFileId(fileId);
        Path targetFilePath = targetDirectory(safeFileId).resolve(safeFileId);
        return Files.exists(targetFilePath);
    }
}