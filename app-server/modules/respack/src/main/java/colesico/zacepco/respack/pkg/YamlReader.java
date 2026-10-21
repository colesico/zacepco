package colesico.zacepco.respack.pkg;

import module org.yaml.snakeyaml;
import jakarta.inject.Provider;
import jakarta.inject.Singleton;

import java.io.*;

/**
 * Yaml document reader
 */
@Singleton
public class YamlReader {

    /**
     * Snake YAML instance is not thread safe
     */
    private final Provider<Yaml> yaml;

    public YamlReader(Provider<Yaml> yaml) {
        this.yaml = yaml;
    }

    public <D> D read(InputStream is, Class<D> documentClass) {
        return yaml.get().loadAs(is, documentClass);
    }

    public <D> D read(File file, Class<D> documentClass) {
        try (FileInputStream fis = new FileInputStream(file)) {
            return read(fis, documentClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
