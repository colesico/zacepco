package colesico.zacepco.script.test;

import colesico.framework.ioc.Ioc;
import colesico.framework.ioc.IocBuilder;
import colesico.framework.ioc.key.ClassedKey;
import colesico.zacepco.script.pkg.*;
import colesico.zacepco.script.model.scriptdoc.ScriptDocument;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScriptTest {

    private Ioc ioc;
    private Path userDir;
    private Path scriptDir;

    @BeforeClass
    public void init() {
        userDir = Paths.get(System.getProperty("user.dir"));
        scriptDir = userDir.resolve("../../../scripts/example");
        ioc = IocBuilder.create().build();
    }

    @Test
    public void testCreateScriptPackage() throws IOException {
        YamlReader reader = ioc.instance(YamlReader.class);

        // Initial script document
        var scriptFile = "script.yaml";
        ScriptDocument script = reader.read(Files.newInputStream(scriptDir.resolve(scriptFile)), ScriptDocument.class);

        try (ScriptPackage scriptPackage = ioc.instance(ScriptPackage.class)) {
            ScriptDocumnetResource scriptDocumnetResource = scriptPackage.scriptDocument();
            scriptDocumnetResource.write(script);
            // Target script file
            scriptPackage.exportTo(userDir.resolve("target/script.zsp.zip").toFile());
        }

    }

    @Test
    public void testCreateScriptPackageFromDir() throws IOException {

        var scriptPackage = ioc.instance(
                new ClassedKey<>(ScriptPackage.class, DirectoryPackageDriver.class),
                scriptDir);

        var resList = scriptPackage.driver().listResources();
        for (ResourcePath rp : resList) {
            IO.println("resource: " + rp.asString());
        }

        scriptPackage.exportTo(userDir.resolve("target/example.zsp.zip").toFile());


    }

}
