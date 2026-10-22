package colesico.zacepco.casereg.srv.service;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.casereg.srv.model.ScriptAssetType;
import colesico.zacepco.common.srv.utils.ImageUtils;
import colesico.zacepco.filestore.service.FileStorage;
import colesico.zacepco.respack.pkg.Resource;
import colesico.zacepco.respack.pkg.ResourcePackage;

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

    protected Path assetsRoot(Long scriptEntryId, ScriptAssetType assetType) {
        return Path.of("assets-" + assetType.resourceDir() + "-" + scriptEntryId);
    }

    public void createAssets(Long scriptEntryId, ResourcePackage resourcePackage) {
        try {

            var posterResource = resourcePackage.scriptImage();
            createThumb(scriptEntryId, posterResource);
            var script = resourcePackage.script().read();

            for (var personage : script.setting.personages) {
                var personageResource = resourcePackage.entityImage(personage.asset());
                createThumb(scriptEntryId, personageResource);
            }

            for (var location : script.setting.scene.locations) {
                var locationResource = resourcePackage.entityImage(location.asset());
                createThumb(scriptEntryId, locationResource);
            }

            for (var clue : script.setting.clues) {
                var clueResource = resourcePackage.entityImage(clue.asset());
                createThumb(scriptEntryId, clueResource);
            }

            for (var detective : script.setting.detectives) {
                var detResource = resourcePackage.entityImage(detective.asset());
                createThumb(scriptEntryId, detResource);
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void createThumb(Long scriptEntryId, Resource resource) {
        try {
            var srcImg = ImageIO.read(resource.inputStream());
            var thumbImg = ImageUtils.iconize(srcImg, 240, 240, 0.5f, 0.5f);
            var pngBytes = ImageUtils.toPngBytes(thumbImg, 0.9f);
            var imgOs = fileStorage.fileOutput(assetsRoot(scriptEntryId, ScriptAssetType.THUMB).resolve(resource.path().path()));
            imgOs.write(pngBytes);
            imgOs.flush();
            imgOs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void readAsset(Long scriptEntryId, Resource resource, OutputStream os) {
        try (
                var is = fileStorage.fileInput(assetsRoot(scriptEntryId, ScriptAssetType.THUMB).resolve(resource.path().path()));
        ) {
            is.transferTo(os);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
