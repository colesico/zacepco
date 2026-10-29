package colesico.zacepco.casereg.srv.service;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.casereg.srv.model.ImageScale;
import colesico.zacepco.core.srv.utils.ImageUtils;
import colesico.zacepco.filestore.service.FileStorage;
import colesico.zacepco.scriptpack.pkg.Resource;
import colesico.zacepco.scriptpack.pkg.ScriptPackage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Path;

@Service
@Transactional
public class ScriptAssetsService {

    private final FileStorage fileStorage;

    public ScriptAssetsService(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    protected Path scaledImagesRoot(Long scriptEntryId, ImageScale scale) {
        return Path.of(scale.code() + "-" + scriptEntryId);
    }

    public InputStream imageInput(Long scriptEntryId, Resource resource, ImageScale scale) {
        if (scale.equals(ImageScale.FULL)) {
            try {
                return resource.inputStream();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
        return fileStorage.fileInput(scaledImagesRoot(scriptEntryId, scale).resolve(resource.path().asPath()));
    }

    public void scaleImages(Long scriptEntryId, ScriptPackage scriptPackage) {
        try {

            var posterResource = scriptPackage.scriptImage();
            iconize(scriptEntryId, posterResource, ImageScale.THUMB);
            var script = scriptPackage.script().read();

            for (var personage : script.crime.personages) {
                var personageResource = scriptPackage.entityImage(personage.asset());
                iconize(scriptEntryId, personageResource, ImageScale.THUMB);
            }

            for (var location : script.crime.scene.locations) {
                var locationResource = scriptPackage.entityImage(location.asset());
                iconize(scriptEntryId, locationResource, ImageScale.THUMB);
            }

            for (var clue : script.crime.clues) {
                var clueResource = scriptPackage.entityImage(clue.asset());
                iconize(scriptEntryId, clueResource, ImageScale.THUMB);
            }

            for (var detective : script.investigation.detectives) {
                var detResource = scriptPackage.entityImage(detective.asset());
                iconize(scriptEntryId, detResource, ImageScale.THUMB);
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void iconize(Long scriptEntryId, Resource resource, ImageScale scale) {
        try {
            var srcImg = ImageIO.read(resource.inputStream());
            var thumbImg = ImageUtils.iconize(srcImg, scale.width(), scale.height(), 0.5f, 0.5f);
            var pngBytes = ImageUtils.toPngBytes(thumbImg, 0.9f);
            var imgOs = fileStorage.fileOutput(scaledImagesRoot(scriptEntryId, scale).resolve(resource.path().asPath()));
            imgOs.write(pngBytes);
            imgOs.flush();
            imgOs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
