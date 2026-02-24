package colesico.zacepco.script.pkg;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Script zip package manager
 */
abstract public class PackageManager implements Closeable {

    /**
     * Get resource output stream for writing resource content
     * The stream must be explicitly closed after use.
     */
    abstract public OutputStream getOutputStream(ResourcePath resourcePath) throws IOException;

    /**
     * Get resource input stream for reading resource content
     * The stream must be explicitly closed after use.
     */
    abstract public InputStream getInputStream(ResourcePath resourcePath) throws IOException;

    /**
     * Remove resource
     */
    abstract public void remove(ResourcePath resourcePath);

    /**
     * List all package resources
     */
    abstract public ResourcePath[] listResources();

    /**
     * Load zip package
     */
    public void load(InputStream is) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                ResourcePath resourcePath = ResourcePath.of(zipEntry.getName());
                try (OutputStream os = getOutputStream(resourcePath)) {
                    zis.transferTo(os);
                }
                zis.closeEntry();
            }
        }
    }

    /**
     * Write zip package to output stream
     */
    public void write(OutputStream os) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(os)) {
            for (ResourcePath resourcePath : listResources()) {
                ZipEntry entry = new ZipEntry(resourcePath.value());
                zos.putNextEntry(entry);
                try (InputStream is = getInputStream(resourcePath)) {
                    is.transferTo(zos);
                }
                zos.closeEntry();
            }
        }
    }

    public void write(Path file) throws IOException {
        try (OutputStream os = Files.newOutputStream(file)) {
            write(os);
        }
    }

}
