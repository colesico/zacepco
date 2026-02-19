package colesico.zacepco.script.spec.assist;

import colesico.zacepco.script.spec.model.script.Script;
import jakarta.inject.Provider;
import org.yaml.snakeyaml.Yaml;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class ScriptWriter {

    private final Provider<Yaml> yamlProvider;

    public ScriptWriter(Provider<Yaml> yamlProvider) {
        this.yamlProvider = yamlProvider;
    }

    public void serialize(Script script, OutputStream os) {
        Yaml yaml = yamlProvider.get();
        OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
        yaml.dump(script, osw);
    }
}
