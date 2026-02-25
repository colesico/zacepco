package colesico.zacepco.script.pkg;

import colesico.zacepco.script.model.entity.EntityId;
import colesico.zacepco.script.model.entity.EntityType;
import jakarta.inject.Provider;

import java.io.Closeable;
import java.io.IOException;

/**
 * cript zip package helper
 */
public class ScriptPackage implements Closeable {

    static final String SCRIPT_DOC = "script.yaml";
    static final String POSTER_IMG = "script.png";

    static final String ENTITY_DIR_PREFIX = "entity_";
    static final String ENTITY_IMG_SUFFIX = ".png";
    static final String ENTITY_TMPL_IMG = "template.png";

    final PackageManager packageManager;
    final Provider<ScriptReader> readerProvider;
    final Provider<ScriptWriter> writerProvider;

    public ScriptPackage(PackageManager packageManager,
                         Provider<ScriptReader> readerProvider,
                         Provider<ScriptWriter> writerProvider) {

        this.packageManager = packageManager;
        this.readerProvider = readerProvider;
        this.writerProvider = writerProvider;
    }

    /**
     * Returns package manager
     */
    public PackageManager getPackageManager() {
        return packageManager;
    }

    /**
     * Returns script resource helper
     */
    public ScriptResource getScript() {
        return new ScriptResource(
                ResourcePath.of(SCRIPT_DOC),
                packageManager,
                readerProvider,
                writerProvider);
    }

    public PackageResource getPoster() {
        return new PackageResource(ResourcePath.of(POSTER_IMG), packageManager);
    }

    public PackageResource getEntityImage(EntityId entityId) {
        String path = ENTITY_DIR_PREFIX + entityId.getType().code() + "/" + entityId.getValue() + ENTITY_IMG_SUFFIX;
        return new PackageResource(ResourcePath.of(path), packageManager);
    }

    public PackageResource getEntityTemplateImage(EntityType entityType) {
        String path = ENTITY_DIR_PREFIX + entityType.code() + "/" + ENTITY_TMPL_IMG;
        return new PackageResource(ResourcePath.of(path), packageManager);
    }

    @Override
    public void close() throws IOException {
        packageManager.close();
    }
}
