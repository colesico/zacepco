package colesico.zacepco.filestorage.config;

import colesico.framework.assist.StringUtils;
import colesico.framework.config.Config;
import colesico.framework.config.UseFileSource;

import java.nio.file.Path;
import java.nio.file.Paths;

@Config
@UseFileSource(file = "storage.properties")
public class FileStorageConfig {

    public static final String STORAGE_PATH_PARAM = "ZACEPCO_STORAGE_PATH";

    private String hashAlgorithm;

    /**
     * Storage root directory
     */
    private String storagePath;

    public Path storagePath() {
        return Paths.get(storagePath).toAbsolutePath().normalize();
    }

    public String getHashAlgorithm() {
        return hashAlgorithm != null ? hashAlgorithm : "SHA-256";
    }

    public void setHashAlgorithm(String hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        var storagePathEnv = System.getProperty(STORAGE_PATH_PARAM);
        if (!StringUtils.isBlank(storagePathEnv)) {
            this.storagePath = storagePathEnv;
        } else {
            this.storagePath = storagePath;
        }
    }
}
