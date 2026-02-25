package colesico.zacepco.script.pkg;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Package resource helper
 */
public class PackageResource {

    /**
     * Resource path
     */
    protected final ResourcePath path;
    protected final PackageManager packageManager;

    public PackageResource(ResourcePath path, PackageManager packageManager) {
        this.path = path;
        this.packageManager = packageManager;
    }

    /**
     * Resource path inside script package
     */
    public ResourcePath getPath() {
        return path;
    }

    /**
     * Input stream for reading resource file content.
     * Stream must be closed after reading.
     */
    public InputStream getInputStream() throws IOException {
        return packageManager.getInputStream(path);
    }

    /**
     * Output stream for writing resource file content.
     * Stream must be closed after writing.
     */
    public OutputStream getOutputStream() throws IOException {
        return packageManager.getOutputStream(path);
    }

    /**
     * Remove resource file from package
     */
    public void remove() {
        packageManager.remove(path);
    }

}
