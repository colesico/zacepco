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

    private final Provider<ScriptReader> reader;
    private final Provider<ScriptWriter> writer;

    public ScriptResource(ResourcePath path,
                          PackageManager packageManager,
                          Provider<ScriptReader> reader,
                          Provider<ScriptWriter> writer) {

        super(path, packageManager);
        this.reader = reader;
        this.writer = writer;
    }

    public Script read() throws IOException {
        try (InputStream is = inputStream()) {
            return reader.get().read(is);
        }
    }

    public void write(Script script) throws IOException {
        try (OutputStream os = outputStream()) {
            writer.get().write(script, os);
        }
    }
}
