package colesico.zacepco.db.filestorage;

import colesico.framework.config.Config;
import colesico.framework.config.UseFileSource;

import java.nio.file.Path;
import java.nio.file.Paths;

@Config
@UseFileSource(file = "META-INF/storage.properties")
public class FileStorageConfig {

    private String hashAlgorithm;

    /**
     * Storage root directory
     */
    private String storageRoot;

    public Path storagePath() {
        return Paths.get(storageRoot).toAbsolutePath().normalize();
    }

    public String getHashAlgorithm() {
        return hashAlgorithm != null ? hashAlgorithm : "SHA-256";
    }

    public void setHashAlgorithm(String hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }

    public String getStorageRoot() {
        return storageRoot;
    }

    public void setStorageRoot(String storageRoot) {
        this.storageRoot = storageRoot;
    }
}
