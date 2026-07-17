package colesico.zacepco.script.pkg;

import colesico.framework.ioc.scope.Unscoped;
import colesico.zacepco.script.model.setting.EntityId;
import colesico.zacepco.script.model.setting.EntityType;
import jakarta.inject.Provider;

import java.io.*;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Script package
 */
@Unscoped
public class ScriptPackage implements Closeable {

    /**
     * Script document
     */
    static final String SCRIPT_DOCUMENT = "script.yaml";

    /**
     * Script poster
     */
    static final String SCRIPT_POSTER = "script.png";

    static final String ENTITY_IMAGE_SUFFIX = ".png";

    static final String ENTITY_TEMPLATE_IMAGE = "template.png";

    protected static final Pattern[] validResources = {
            Pattern.compile("^script.(yaml|png)$"),
            Pattern.compile("^(L/L|P/P|C/C)\\d+.png$"),
            Pattern.compile("^[PCL]/template.png$"),
    };

    /**
     * This script package manager
     */
    final PackageDriver packageDriver;

    final Provider<ScriptReader> reader;

    final Provider<ScriptWriter> writer;

    public ScriptPackage(PackageDriver packageDriver,
                         Provider<ScriptReader> reader,
                         Provider<ScriptWriter> writer) {

        this.packageDriver = packageDriver;
        this.reader = reader;
        this.writer = writer;
    }

    /**
     * Returns package manager for this script package
     */
    public PackageDriver packageManager() {
        return packageDriver;
    }

    /**
     * Returns script document resource
     */
    public ScriptResource script() {
        return new ScriptResource(
                ResourcePath.of(SCRIPT_DOCUMENT),
                packageDriver,
                reader,
                writer);
    }

    /**
     * Returns script poster resource
     */
    public PackageResource poster() {
        return new PackageResource(ResourcePath.of(SCRIPT_POSTER), packageDriver);
    }

    public PackageResource entityImage(EntityId entityId) {
        String path = entityId.getType().code() + "/" + entityId.getValue() + ENTITY_IMAGE_SUFFIX;
        return new PackageResource(ResourcePath.of(path), packageDriver);
    }

    public PackageResource entityTemplateImage(EntityType entityType) {
        String path = entityType.code() + "/" + ENTITY_TEMPLATE_IMAGE;
        return new PackageResource(ResourcePath.of(path), packageDriver);
    }

    /**
     * Export package to zip file
     */
    public void exportTo(File scriptZip) throws IOException {
        try (OutputStream os = Files.newOutputStream(scriptZip.toPath())) {
            packageDriver.exportPackage(os);
        }
    }

    public void exportTo(OutputStream os) throws IOException {
        packageDriver.exportPackage(os);
    }

    public void importFrom(File scriptPackage) throws IOException {
        try (InputStream is = Files.newInputStream(scriptPackage.toPath())) {
            packageDriver.importPackage(is, this::validate);
        }
    }

    public void importFrom(InputStream is) throws IOException {
        packageDriver.importPackage(is, this::validate);
    }

    protected void validate(ResourcePath resourcePath) {
        for (Pattern pattern : validResources) {
            Matcher matcher = pattern.matcher(resourcePath.value());
            if (matcher.matches()) {
                return;
            }
        }
        throw new RuntimeException("Invalid script package resource path: " + resourcePath);
    }

    @Override
    public void close() throws IOException {
        packageDriver.close();
    }
}
