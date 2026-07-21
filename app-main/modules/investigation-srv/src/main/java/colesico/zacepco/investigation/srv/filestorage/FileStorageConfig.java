package colesico.zacepco.investigation.srv.filestorage;

import colesico.framework.config.Config;
import colesico.framework.config.UseFileSource;

import java.nio.file.Path;
import java.nio.file.Paths;

@Config
@UseFileSource(file = "filestorage.properties")
public class FileStorageConfig {

    private String hashAlgorithm;

    private String storageDirectory;

    public Path storagePath() {
        return Paths.get(storageDirectory).toAbsolutePath().normalize();
    }

    public String getHashAlgorithm() {
        return hashAlgorithm != null ? hashAlgorithm : "SHA-256";
    }

    public void setHashAlgorithm(String hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }

    public String getStorageDirectory() {
        return storageDirectory;
    }

    public void setStorageDirectory(String storageDirectory) {
        this.storageDirectory = storageDirectory;
    }
}
