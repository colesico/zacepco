package colesico.zacepco.script.pkg;

import module org.yaml.snakeyaml;
import colesico.zacepco.script.model.script.Script;
import jakarta.inject.Provider;
import jakarta.inject.Singleton;

import java.io.*;

/**
 * Script file reader
 */
@Singleton
public class ScriptReader {

    private final Provider<Yaml> yaml;

    public ScriptReader(Provider<Yaml> yaml) {
        this.yaml = yaml;
    }

    public Script read(InputStream is) {
        Yaml yaml = this.yaml.get();
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
