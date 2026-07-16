package colesico.zacepco.investigation.filestorage;

import jakarta.inject.Singleton;

import java.io.InputStream;

@Singleton
public class FileStorage {

    private final FileStorageConfig config;

    public FileStorage(FileStorageConfig config) {
        this.config = config;
    }

    public void input(String fileId, InputStream fileData){

    }

    public void output(String fileId){

    }

    public void delete(String fileId){

    }

    public boolean exists(String fileId){

    }


    public static Path buildStoragePath(String rootDir, String fileUuid, String extension) {
        // fileUuid например: "4a7d1ed4-bc32..."
        String dir1 = fileUuid.substring(0, 2); // "4a"
        String dir2 = fileUuid.substring(2, 4); // "7d"

        // Вернет: rootDir/4a/7d/4a7d1ed4-bc32....jpg
        return Paths.get(rootDir, dir1, dir2, fileUuid + extension);
    }
}
