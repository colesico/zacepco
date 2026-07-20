package colesico.zacepco.script.pkg;

import module org.yaml.snakeyaml;
import colesico.framework.ioc.scope.Unscoped;
import colesico.zacepco.script.model.script.Script;

import java.io.*;

/**
 * Script file reader
 */
@Unscoped
public class ScriptReader {

    private final Yaml yaml;

    public ScriptReader(Yaml yaml) {
        this.yaml = yaml;
    }

    public Script read(InputStream is) {
        return yaml.loadAs(is, Script.class);
    }

    public Script read(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            return read(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
