package colesico.zacepco.script.lib.assist;

import  module org.yaml.snakeyaml;
import colesico.zacepco.script.lib.model.Script;
import jakarta.inject.Provider;
import jakarta.inject.Singleton;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@Singleton
public class ScriptIO {

    private Provider<Yaml> yamlProvider;

    public ScriptIO(Provider<Yaml> yamlProvider) {
        this.yamlProvider = yamlProvider;
    }

    public void serialize(Script script, OutputStream os){
        Yaml yaml = yamlProvider.get();
        OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
        yaml.dump(script,osw);
    }

    public Script deserialize(InputStream is){
        Yaml yaml = yamlProvider.get();
        return yaml.load(is);
    }

}
