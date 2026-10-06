package colesico.zacepco.cases.srv.service;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.cases.srv.model.AssetType;
import colesico.zacepco.common.srv.filestorage.FileStorage;
import colesico.zacepco.common.srv.utils.ImageUtils;
import colesico.zacepco.script.pkg.PackageResource;
import colesico.zacepco.script.pkg.ScriptPackage;

import javax.imageio.ImageIO;
import java.io.OutputStream;
import java.nio.file.Path;

@Service
@Transactional
public class ScriptAssetsService {

    private final FileStorage fileStorage;

    public ScriptAssetsService(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    protected Path assetsRoot(Long scriptEntryId, AssetType assetType) {
        return Path.of("assets-" + assetType.resourceDir() + "-" + scriptEntryId);
    }

    public void createAssets(Long scriptEntryId, ScriptPackage scriptPackage) {
        try {

            var posterResource = scriptPackage.scriptImage();
            createThumb(scriptEntryId, posterResource);
            var script = scriptPackage.script().read();

            for (var personage : script.setting.personages) {
                var personageResource = scriptPackage.entityImage(personage.id);
                createThumb(scriptEntryId, personageResource);
            }

            for (var location : script.setting.scene.locations) {
                var locationResource = scriptPackage.entityImage(location.id);
                createThumb(scriptEntryId, locationResource);
            }

            for (var clue : script.setting.clues) {
                var clueResource = scriptPackage.entityImage(clue.id);
                createThumb(scriptEntryId, clueResource);
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void createThumb(Long scriptEntryId, PackageResource resource) {
        try {
            var srcImg = ImageIO.read(resource.inputStream());
            var thumbImg = ImageUtils.iconize(srcImg, 240, 240, 0.5f, 0.5f);
            var pngBytes = ImageUtils.toPngBytes(thumbImg, 0.9f);
            var imgOs = fileStorage.fileOutput(assetsRoot(scriptEntryId, AssetType.THUMB).resolve(resource.path().path()));
            imgOs.write(pngBytes);
            imgOs.flush();
            imgOs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void readAsset(Long scriptEntryId, PackageResource resource, OutputStream os) {
        try (
                var is = fileStorage.fileInput(assetsRoot(scriptEntryId, AssetType.THUMB).resolve(resource.path().path()));
        ) {
            is.transferTo(os);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
