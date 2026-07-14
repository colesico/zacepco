package colesico.zacepco.script.pkg;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represents resource inside package
 */
public class PackageResource {

    /**
     * Resource path inside package
     */
    protected final ResourcePath path;

    /**
     * Package manager to perform resource operations
     */
    protected final PackageManager packageManager;

    public PackageResource(ResourcePath path, PackageManager packageManager) {
        this.path = path;
        this.packageManager = packageManager;
    }

    /**
     * Resource path inside script package
     */
    public ResourcePath path() {
        return path;
    }

    /**
     * Input stream for reading resource.
     * Stream must be closed after reading.
     */
    public InputStream inputStream() throws IOException {
        return packageManager.resourceInput(path);
    }

    /**
     * Output stream for writing resource.
     * Stream must be closed after writing.
     */
    public OutputStream outputStream() throws IOException {
        return packageManager.resourceOutput(path);
    }

    /**
     * Remove resource  from package
     */
    public void remove() throws IOException {
        packageManager.removeResource(path);
    }

}
