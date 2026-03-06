package colesico.zacepco.script.pkg;

import colesico.zacepco.script.model.entity.EntityId;
import colesico.zacepco.script.model.entity.EntityType;
import jakarta.inject.Provider;

import java.io.*;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Script zip package helper
 */
public class ScriptPackage implements Closeable {

    static final String SCRIPT_DOC = "script.yaml";
    static final String POSTER_IMG = "script.png";

    static final String ENTITY_IMG_SUFFIX = ".png";
    static final String ENTITY_TMPL_IMG = "template.png";

    protected static final Pattern[] validResources = {
            Pattern.compile("^script.(yaml|png)$"),
            Pattern.compile("^(L/L|P/P|C/C)\\d+.png$"),
            Pattern.compile("^[PCL]/template.png$"),
    };

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
        String path = entityId.getType().code() + "/" + entityId.getValue() + ENTITY_IMG_SUFFIX;
        return new PackageResource(ResourcePath.of(path), packageManager);
    }

    public PackageResource getEntityTemplateImage(EntityType entityType) {
        String path = entityType.code() + "/" + ENTITY_TMPL_IMG;
        return new PackageResource(ResourcePath.of(path), packageManager);
    }

    public void write(File scriptPackage) throws IOException {
        try (OutputStream os = Files.newOutputStream(scriptPackage.toPath())) {
            packageManager.write(os);
        }
    }

    public void write(OutputStream os) throws IOException {
        packageManager.write(os);
    }

    public void load(File scriptPackage) throws IOException {
        try (InputStream is = Files.newInputStream(scriptPackage.toPath())) {
            packageManager.load(is, this::validate);
        }
    }

    protected void validate(ResourcePath resourcePath){
        for (Pattern pattern : validResources) {
            Matcher matcher = pattern.matcher(resourcePath.value());
            if (matcher.matches()) {
                return;
            }
        }
        throw new RuntimeException("Invalid script package resource path: " + resourcePath);
    }

    public void load(InputStream is) throws IOException {
        packageManager.load(is, this::validate);
    }

    @Override
    public void close() throws IOException {
        packageManager.close();
    }
}
