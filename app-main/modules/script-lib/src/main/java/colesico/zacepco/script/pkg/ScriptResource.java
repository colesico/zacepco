package colesico.zacepco.script.pkg;

import colesico.zacepco.script.model.script.Script;
import jakarta.inject.Provider;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represents script document resource (script.yaml)
 */
public class ScriptResource extends PackageResource {

    private final ScriptReader reader;
    private final ScriptWriter writer;

    public ScriptResource(ResourcePath path, PackageDriver packageDriver, ScriptReader reader, ScriptWriter writer) {
        super(path, packageDriver);
        this.reader = reader;
        this.writer = writer;
    }

    public Script read() throws IOException {
        try (InputStream is = inputStream()) {
            return reader.read(is);
        }
    }

    public void write(Script script) throws IOException {
        try (OutputStream os = outputStream()) {
            writer.write(script, os);
        }
    }
}
