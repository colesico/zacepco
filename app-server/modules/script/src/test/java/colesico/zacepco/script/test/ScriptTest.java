package colesico.zacepco.script.test;

import colesico.framework.ioc.Ioc;
import colesico.framework.ioc.IocBuilder;
import colesico.framework.ioc.key.ClassedKey;
import colesico.zacepco.script.pkg.*;
import colesico.zacepco.script.model.script.Script;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScriptTest {

    private Ioc ioc;

    @BeforeClass
    public void init() {
        ioc = IocBuilder.create().build();
    }

    @Test
    public void testCreateScriptPackage() throws IOException {
        ScriptReader reader = ioc.instance(ScriptReader.class);

        // Initial script document
        var scriptFile = "script.yaml";
        var classLoader = Thread.currentThread().getContextClassLoader();
        Script script = reader.read(classLoader.getResourceAsStream(scriptFile));

        try (ScriptPackage scriptPackage = ioc.instance(ScriptPackage.class)) {
            ScriptResource scriptResource = scriptPackage.script();
            scriptResource.write(script);

            Path targetPath = Paths.get(System.getProperty("user.dir"));
            // Target script file
            scriptPackage.exportTo(targetPath.resolve("target/script.zsp.zip").toFile());
        }

    }

    @Test
    public void testCreateScriptPackageFromDir() throws IOException {
        Path userDir = Paths.get(System.getProperty("user.dir"));
        var scriptDir = userDir.resolve("../../../scripts/default");

        var scriptPackage = ioc.instance(
                new ClassedKey<>(ScriptPackage.class, DirectoryPackageDriver.class),
                scriptDir);

        var resList = scriptPackage.driver().listResources();
        for (ResourcePath rp : resList) {
            IO.println("resource: " + rp.value());
        }

        scriptPackage.exportTo(userDir.resolve("target/default.zsp.zip").toFile());


    }

}
