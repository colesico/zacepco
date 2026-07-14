package colesico.zacepco.script.pkg;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Abstract package manager.
 * Represents package basic operations.
 */
abstract public class PackageManager implements Closeable {

    protected static final int MAX_ZIP_ENTRIES = 1024;

    /**
     * Output stream for writing resource to package.
     * Unexisted resource will be created, existed - rewrote.
     * Output stream must be explicitly closed after use.
     */
    abstract public OutputStream resourceOutput(ResourcePath resourcePath) throws IOException;

    /**
     * Input stream for reading resource from package.
     * The stream must be explicitly closed after use.
     */
    abstract public InputStream resourceInput(ResourcePath resourcePath) throws IOException;

    /**
     * Remove resource from package
     */
    abstract public void removeResource(ResourcePath resourcePath) throws IOException;

    /**
     * List all resources inside package
     */
    abstract public Collection<ResourcePath> listResources() throws IOException;

    /**
     * Import package from zip archive
     *
     * @param is stream to read zip bytes
     */
    public void importPackage(InputStream is, Consumer<ResourcePath> pathValidator) throws IOException {
        if (pathValidator == null) {
            pathValidator = resourcePath -> {
            };
        }
        try (ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry zipEntry;
            int entriesCount = 0;
            while ((zipEntry = zis.getNextEntry()) != null) {
                if (++entriesCount > MAX_ZIP_ENTRIES) {
                    throw new IOException("Too many zip entries. Max = " + MAX_ZIP_ENTRIES);
                }
                if (zipEntry.isDirectory()) {
                    continue;
                }
                ResourcePath resourcePath = ResourcePath.of(zipEntry.getName());
                // Validate resource path
                pathValidator.accept(resourcePath);
                try (OutputStream os = resourceOutput(resourcePath)) {
                    zis.transferTo(os);
                }
                zis.closeEntry();
            }
        }
    }

    /**
     * Export package to zip archive
     *
     * @param os stream to write zip bytes
     */
    public void exportPackage(OutputStream os) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(os)) {
            for (ResourcePath resourcePath : listResources()) {
                ZipEntry entry = new ZipEntry(resourcePath.value());
                zos.putNextEntry(entry);
                try (InputStream is = resourceInput(resourcePath)) {
                    is.transferTo(zos);
                }
                zos.closeEntry();
            }
        }
    }

}
