package colesico.zacepco.script.pkg;

import colesico.zacepco.script.model.scriptdoc.ScriptDocument;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;

/**
 * Represents script document resource (script.yaml)
 */
public class ScriptDocumnetResource extends Resource {

    private final YamlReader reader;
    private final YamlWriter writer;

    public ScriptDocumnetResource(ResourcePath path, PackageDriver packageDriver, YamlReader reader, YamlWriter writer) {
        super(path, packageDriver);
        this.reader = reader;
        this.writer = writer;
    }

    public ScriptDocument read() {
        try (InputStream is = inputStream()) {
            return reader.read(is, ScriptDocument.class);
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    public void write(ScriptDocument script) {
        try (OutputStream os = outputStream()) {
            writer.write(script, os);
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }
}
