package colesico.zacepco.script.pkg;

import module org.yaml.snakeyaml;
import colesico.framework.ioc.production.Supplier;
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
    private final Supplier<ReadPostprocessor> postprocessor;

    public YamlReader(Provider<Yaml> yaml, Supplier<ReadPostprocessor> postprocessor) {
        this.yaml = yaml;
        this.postprocessor = postprocessor;
    }

    public <D> D read(InputStream is, Class<D> documentClass) {
        D documnet = yaml.get().loadAs(is, documentClass);
        return (D) postprocessor.get(documentClass).process(documnet);
    }

    public <D> D read(File file, Class<D> documentClass) {
        try (FileInputStream fis = new FileInputStream(file)) {
            return read(fis, documentClass);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
