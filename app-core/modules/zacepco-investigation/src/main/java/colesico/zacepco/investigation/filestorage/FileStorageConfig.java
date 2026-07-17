package colesico.zacepco.investigation.filestorage;

import colesico.framework.config.Config;
import colesico.framework.config.UseFileSource;

@Config
@UseFileSource(file = "filestorage.properties")
public class FileStorageConfig {

    private String hashAlgorithm;

    private String storageDirectory;

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
