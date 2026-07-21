package colesico.zacepco.script.test;

import colesico.framework.ioc.Ioc;
import colesico.framework.ioc.IocBuilder;
import colesico.zacepco.script.lib.pkg.*;
import colesico.zacepco.script.lib.model.script.Script;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
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
    public void testImportScriptPackage() throws IOException {
        var packageFile = "../../../scripts/default.zip";
        try (ScriptPackage scriptPackage = ioc.instance(ScriptPackage.class)) {
            scriptPackage.importFrom(new File(packageFile));
            var resList = scriptPackage.driver().listResources();
            for (ResourcePath rp : resList) {
                IO.println("resource: " + rp.value());
            }

            Path targetPath = Paths.get(System.getProperty("user.dir"));
            scriptPackage.exportTo(targetPath.resolve("target/default.zsp.zip").toFile());
        }


    }

}
