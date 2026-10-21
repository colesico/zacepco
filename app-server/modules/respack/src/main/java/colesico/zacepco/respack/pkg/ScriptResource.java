package colesico.zacepco.respack.pkg;

import colesico.zacepco.respack.model.script.ScriptDocument;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represents script document resource (script.yaml)
 */
public class ScriptResource extends Resource {

    private final YamlReader reader;
    private final YamlWriter writer;

    public ScriptResource(ResourcePath path, PackageDriver packageDriver, YamlReader reader, YamlWriter writer) {
        super(path, packageDriver);
        this.reader = reader;
        this.writer = writer;
    }

    public ScriptDocument read() throws IOException {
        try (InputStream is = inputStream()) {
            return reader.read(is, ScriptDocument.class);
        }
    }

    public void write(ScriptDocument script) throws IOException {
        try (OutputStream os = outputStream()) {
            writer.write(script, os);
        }
    }
}
