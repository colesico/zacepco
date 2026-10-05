package colesico.zacepco.script.pkg;

import module org.yaml.snakeyaml;
import colesico.framework.ioc.scope.Unscoped;
import colesico.zacepco.script.model.script.Script;
import jakarta.inject.Provider;

import java.io.*;

/**
 * Script file reader
 */
@Unscoped
public class ScriptReader {

    /**
     * Snake YAML instance is not thread safe
     */
    private final Provider<Yaml> yaml;

    public ScriptReader(Provider<Yaml> yaml) {
        this.yaml = yaml;
    }

    public Script read(InputStream is) {
        return yaml.get().loadAs(is, Script.class);
    }

    public Script read(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            return read(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
