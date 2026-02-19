package colesico.zacepco.script.spec.assist;

import colesico.zacepco.script.spec.model.script.Script;
import jakarta.inject.Provider;
import jakarta.inject.Singleton;
import org.yaml.snakeyaml.Yaml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@Singleton
public class ScriptWriter {

    private final Provider<Yaml> yamlProvider;

    public ScriptWriter(Provider<Yaml> yamlProvider) {
        this.yamlProvider = yamlProvider;
    }

    public void write(Script script, OutputStreamWriter osw) {
        Yaml yaml = yamlProvider.get();
        yaml.dump(script, osw);
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
