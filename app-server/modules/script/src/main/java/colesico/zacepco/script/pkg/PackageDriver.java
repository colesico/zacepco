package colesico.zacepco.script.pkg;

import java.io.*;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Abstract package diver.
 * Represents package basic operations.
 */
abstract public class PackageDriver implements Closeable {

    protected static final int MAX_ZIP_ENTRIES = 1024;

    /**
     * Output stream for writing resource to package.
     * Unexisted resource will be created, existed - rewrote.
     * Output stream must be explicitly closed after use.
     */
    abstract public OutputStream resourceOutput(ResourcePath resourcePath);

    /**
     * Input stream for reading resource from package.
     * The stream must be explicitly closed after use.
     */
    abstract public InputStream resourceInput(ResourcePath resourcePath);

    /**
     * Remove resource from package
     */
    abstract public void removeResource(ResourcePath resourcePath);

    /**
     * List all resources inside package
     */
    abstract public Collection<ResourcePath> listResources();

    /**
     * Import package from zip archive
     *
     * @param is stream to read zip bytes
     */
    public void importPackage(InputStream is, Consumer<ResourcePath> pathValidator) {
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
            if (entriesCount < 2) {
                throw new RuntimeException("Invalid script package");
            }
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    /**
     * Export package to zip archive
     *
     * @param os stream to write zip bytes
     */
    public void exportPackage(OutputStream os) {
        try (ZipOutputStream zos = new ZipOutputStream(os)) {
            for (ResourcePath resourcePath : listResources()) {
                ZipEntry entry = new ZipEntry(resourcePath.asString());
                zos.putNextEntry(entry);
                try (InputStream is = resourceInput(resourcePath)) {
                    is.transferTo(zos);
                }
                zos.closeEntry();
            }
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

}
