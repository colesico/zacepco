package colesico.zacepco.respack.pkg;

import colesico.zacepco.respack.model.assets.AssetsDocument;
import colesico.zacepco.respack.model.script.ScriptDocument;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represents assets document resource (assets.yaml)
 */
public class AssetsResource extends Resource {

    private final YamlReader reader;
    private final YamlWriter writer;

    public AssetsResource(ResourcePath path, PackageDriver packageDriver, YamlReader reader, YamlWriter writer) {
        super(path, packageDriver);
        this.reader = reader;
        this.writer = writer;
    }

    public AssetsDocument read() throws IOException {
        try (InputStream is = inputStream()) {
            return reader.read(is, AssetsDocument.class);
        }
    }

    public void write(AssetsDocument assets) throws IOException {
        try (OutputStream os = outputStream()) {
            writer.write(assets, os);
        }
    }
}
