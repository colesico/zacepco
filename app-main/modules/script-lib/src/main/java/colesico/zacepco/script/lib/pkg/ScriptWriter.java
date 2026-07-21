package colesico.zacepco.script.lib.pkg;

import colesico.framework.ioc.scope.Unscoped;
import colesico.zacepco.script.lib.model.script.Script;
import org.yaml.snakeyaml.Yaml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@Unscoped
public class ScriptWriter {

    private final Yaml yaml;

    public ScriptWriter(Yaml yaml) {
        this.yaml = yaml;
    }

    public void write(Script script, OutputStreamWriter osw) {
        yaml.dump(script, osw);
    }

    public void write(Script script, OutputStream os) {
        try (OutputStreamWriter osw = new OutputStreamWriter(os)) {
            yaml.dump(script, osw);
            osw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String write(Script script) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (OutputStreamWriter osw = new OutputStreamWriter(baos, StandardCharsets.UTF_8)) {
            write(script, osw);
            osw.flush();
            return baos.toString(StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
