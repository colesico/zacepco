package colesico.zacepco.script.pkg;

import colesico.zacepco.script.model.script.Script;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PackageScriptResource extends PackageResource {
    public PackageScriptResource(ResourcePath path, PackageManager packageManager) {
        super(path, packageManager);
    }

    public Script getScript(ScriptReader reader) throws IOException {
        try (InputStream is = getInputStream()) {
            return reader.read(is);
        }
    }

    public void setScript(Script script, ScriptWriter writer) throws IOException {
        try (OutputStream os = getOutputStream()) {
            writer.write(script, os);
        }
    }
}
