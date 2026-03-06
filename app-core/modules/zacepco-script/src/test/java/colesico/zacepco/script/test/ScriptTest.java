package colesico.zacepco.script.test;

import colesico.framework.ioc.Ioc;
import colesico.framework.ioc.IocBuilder;
import colesico.zacepco.script.pkg.*;
import colesico.zacepco.script.model.script.Script;
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

        var scriptFile = "../../../docs/script.yaml";
        Script script = reader.read(new File(scriptFile));

        try (ScriptPackage scriptPackage = ioc.instance(ScriptPackage.class)) {
            ScriptResource scriptResource = scriptPackage.getScript();
            scriptResource.setScript(script);

            Path targetPath = Paths.get(System.getProperty("user.dir"));
            scriptPackage.write(targetPath.resolve("target/script.zsp.zip").toFile());
        }

    }

    @Test
    public void testLoadScriptPackage() throws IOException {
        var packageFile = "../../../scripts/default.zip";
        try (ScriptPackage scriptPackage = ioc.instance(ScriptPackage.class)) {
            scriptPackage.load(new File(packageFile));
            var resList  = scriptPackage.getPackageManager().listResources();
            for (ResourcePath rp:resList){
                System.out.println("resource: "+rp.value());
            }
        }

    }

}
