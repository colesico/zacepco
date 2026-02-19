package colesico.zacepco.script.spec.assist;

import module org.yaml.snakeyaml;
import colesico.zacepco.script.spec.model.script.Script;
import jakarta.inject.Provider;
import jakarta.inject.Singleton;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Singleton
public class ScriptReader {

    private final Provider<Yaml> yamlProvider;

    public ScriptReader(Provider<Yaml> yamlProvider) {
        this.yamlProvider = yamlProvider;
    }

    public Script read(InputStream is) {
        Yaml yaml = yamlProvider.get();
        return yaml.loadAs(is,Script.class);
    }

    public Script read(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            return read(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
