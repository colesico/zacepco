package colesico.zacepco.script.pkg;

import colesico.zacepco.script.model.script.Script;
import jakarta.inject.Provider;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ScriptResource extends PackageResource {

    private final Provider<ScriptReader> readerProvider;
    private final Provider<ScriptWriter> writerProvider;

    public ScriptResource(ResourcePath path,
                          PackageManager packageManager,
                          Provider<ScriptReader> readerProvider,
                          Provider<ScriptWriter> writerProvider) {

        super(path, packageManager);
        this.readerProvider = readerProvider;
        this.writerProvider = writerProvider;
    }

    public Script getScript() throws IOException {
        try (InputStream is = getInputStream()) {
            return readerProvider.get().read(is);
        }
    }

    public void setScript(Script script) throws IOException {
        try (OutputStream os = getOutputStream()) {
            writerProvider.get().write(script, os);
        }
    }
}
