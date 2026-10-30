package colesico.zacepco.scriptpack.pkg;

import colesico.framework.ioc.message.IocMessage;
import colesico.framework.ioc.scope.Unscoped;
import colesico.zacepco.scriptpack.model.script.EntitySpec;

import java.io.*;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Resource package API
 */
@Unscoped
public class ScriptPackage implements Closeable {

    /**
     * Assets document
     */
    static final String ASSETS_DOCUMENT = "assets.yaml";

    /**
     * Script document
     */
    static final String SCRIPT_DOCUMENT = "script.yaml";

    /**
     * Script poster
     */
    static final String SCRIPT_IMAGE = "script.png";

    static final String ENTITY_IMAGE_SUFFIX = ".png";

    static final String ENTITY_TEMPLATE_IMAGE = "template.png";

    protected static final Pattern[] validResources = {
            Pattern.compile("^script\\.(yaml|png)$"),
            Pattern.compile("^(P/P|C/C|L/L|D/D)[A-Z0-9]{1,3}(\\.png)$"),
            Pattern.compile("^([PCLD])/card\\.png$"),
    };

    /**
     * This script package manager
     */
    final PackageDriver packageDriver;

    final YamlReader reader;

    final YamlWriter writer;

    public ScriptPackage(@IocMessage PackageDriver packageDriver,
                         YamlReader reader,
                         YamlWriter writer) {

        if (packageDriver == null) {
            throw new RuntimeException("Package driver is not specified");
        }
        this.packageDriver = packageDriver;
        this.reader = reader;
        this.writer = writer;
    }

    /**
     * Returns driver for this script package
     */
    public PackageDriver driver() {
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
    public Resource scriptImage() {
        return new Resource(ResourcePath.of(SCRIPT_IMAGE), packageDriver);
    }

    public Resource entityImage(EntitySpec.UID uid) {
        return new Resource(ResourcePath.of(uid.type().code + "/" + uid + ENTITY_IMAGE_SUFFIX), packageDriver);
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
            Matcher matcher = pattern.matcher(resourcePath.asString());
            if (matcher.matches()) {
                return;
            }
        }
        throw new RuntimeException("Invalid script package resource path: '" + resourcePath + "'");
    }

    @Override
    public void close() throws IOException {
        packageDriver.close();
    }
}
