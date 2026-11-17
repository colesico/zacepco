package colesico.zacepco.docket.srv.service;

import colesico.framework.service.PlainMethod;
import colesico.framework.service.Service;
import colesico.zacepco.core.srv.utils.ImageUtils;
import colesico.zacepco.docket.srv.model.ImageScale;
import colesico.zacepco.filestore.service.FileStorage;
import colesico.zacepco.script.model.scriptdoc.EntitySpec;
import colesico.zacepco.script.pkg.Resource;
import colesico.zacepco.script.pkg.ScriptPackage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Stream;

@Service
public class ScriptAssetService {

    private final FileStorage fileStorage;

    public ScriptAssetService(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    protected Path scaledImagesRoot(Long scriptEntryId, ImageScale scale) {
        return Path.of("script-" + scriptEntryId, "_" + scale.code());
    }

    public InputStream imageInput(Long scriptEntryId, Resource resource, ImageScale scale) {
        if (scale == ImageScale.FULL) {
            return resource.inputStream();
        }
        return fileStorage.fileInput(scaledImagesRoot(scriptEntryId, scale).resolve(resource.path().asPath()));
    }

    @PlainMethod
    public void scaleImages(Long scriptEntryId, ScriptPackage scriptPackage) {
        var posterResource = scriptPackage.scriptImage();
        iconize(scriptEntryId, posterResource, ImageScale.THUMB, posterResource);

        var script = scriptPackage.scriptDocument().read();
        Stream.of(
                        script.crime.personages.values(),
                        script.crime.scene.locations.values(),
                        script.crime.clues.values(),
                        script.investigation.detectives.values()
                )
                .flatMap(Collection::stream)
                .forEach(entity -> iconize(entity, ImageScale.THUMB, scriptEntryId, scriptPackage));
    }

    @PlainMethod
    public void iconize(EntitySpec<?> entity, ImageScale scale, Long scriptEntryId, ScriptPackage scriptPackage) {
        var srcId = entity.asset != null ? entity.asset : entity.id;
        var srcResource = scriptPackage.entityImage(srcId);
        var dstResource = scriptPackage.entityImage(entity.id);
        iconize(scriptEntryId, srcResource, scale, dstResource);
    }

    @PlainMethod
    public void iconize(Long scriptEntryId, Resource srcResource, ImageScale scale, Resource dstResource) {
        Path targetPath = scaledImagesRoot(scriptEntryId, scale).resolve(dstResource.path().asPath());
        try (var srcIn = srcResource.inputStream();
             var imgOs = fileStorage.fileOutput(targetPath)) {
            var srcImg = ImageIO.read(srcIn);
            var thumbImg = ImageUtils.iconize(srcImg, scale.width(), scale.height(), 0.5f, 0.5f);
            var pngBytes = ImageUtils.toPngBytes(thumbImg, 0.9f);
            imgOs.write(pngBytes);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
