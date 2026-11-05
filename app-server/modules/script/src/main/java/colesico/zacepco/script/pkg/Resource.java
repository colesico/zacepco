package colesico.zacepco.script.pkg;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represents resource inside package
 */
public class Resource {

    /**
     * Resource path inside package
     */
    protected final ResourcePath path;

    /**
     * Package manager to perform resource operations
     */
    protected final PackageDriver packageDriver;

    public Resource(ResourcePath path, PackageDriver packageDriver) {
        this.path = path;
        this.packageDriver = packageDriver;
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
    public InputStream inputStream() {
        return packageDriver.resourceInput(path);
    }

    /**
     * Output stream for writing resource.
     * Stream must be closed after writing.
     */
    public OutputStream outputStream() {
        return packageDriver.resourceOutput(path);
    }

    /**
     * Remove resource  from package
     */
    public void remove() {
        packageDriver.removeResource(path);
    }

}
