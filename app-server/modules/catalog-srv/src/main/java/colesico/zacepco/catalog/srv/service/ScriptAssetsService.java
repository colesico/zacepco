package colesico.zacepco.catalog.srv.service;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.common.srv.filestorage.FileStorage;
import colesico.zacepco.common.srv.utils.ImageUtils;
import colesico.zacepco.script.pkg.PackageResource;
import colesico.zacepco.script.pkg.ResourcePath;
import colesico.zacepco.script.pkg.ScriptPackage;

import javax.imageio.ImageIO;
import java.io.InputStream;
import java.nio.file.Path;

@Service
@Transactional
public class ScriptAssetsService {

    private final FileStorage fileStorage;

    public ScriptAssetsService(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    protected Path assetsRoot(Long entryId) {
        return Path.of("assets" + entryId);
    }

    public void createAssets(Long entryId, ScriptPackage scriptPackage) {
        try {

            var posterResource = scriptPackage.poster();
            createThumb(entryId, posterResource;
            var script = scriptPackage.script().read();

            for (var clue : script.setting.clues) {
                var clueResource = scriptPackage.entityImage(clue.id);
                createThumb(entryId, clueResource);
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void createThumb(Long entryId, PackageResource resource) {
        try {
            var srcImg = ImageIO.read(resource.inputStream());
            var thumbImg = ImageUtils.iconize(srcImg, 240, 240, 0.5f, 0.5f);
            var pngBytes = ImageUtils.toPngBytes(thumbImg, 0.9f);
            var imgOs = fileStorage.fileOutput(assetsRoot(entryId).resolve(resource.path().path()));
            imgOs.write(pngBytes);
            imgOs.flush();
            imgOs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
