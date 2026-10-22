package colesico.zacepco.scriptpack.pkg;

import jakarta.inject.Provider;
import jakarta.inject.Singleton;
import org.yaml.snakeyaml.Yaml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@Singleton
public class YamlWriter {

    /**
     * Snake YAML instance is not thread safe
     */
    private final Provider<Yaml> yaml;

    public YamlWriter(Provider<Yaml> yaml) {
        this.yaml = yaml;
    }

    public <D> void write(D document, OutputStreamWriter osw) {
        yaml.get().dump(document, osw);
    }

    public <D> void write(D document, OutputStream os) {
        try (OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            write(document, osw);
            osw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <D> String write(D document) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (OutputStreamWriter osw = new OutputStreamWriter(baos, StandardCharsets.UTF_8)) {
            write(document, osw);
            osw.flush();
            return baos.toString(StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
