package colesico.zacepco.script.test;

import colesico.framework.ioc.Ioc;
import colesico.framework.ioc.IocBuilder;
import colesico.zacepco.script.pkg.ScriptReader;
import colesico.zacepco.script.pkg.ScriptWriter;
import colesico.zacepco.script.model.script.Script;
import colesico.zacepco.script.pkg.PackageResource;
import colesico.zacepco.script.pkg.ScriptPackage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScriptLoadTest {

    private Ioc ioc;

    @BeforeClass
    public void init() {
        ioc = IocBuilder.create().build();
    }

    @Test
    public void testLoadScript() throws IOException {
        ScriptReader reader = ioc.instance(ScriptReader.class);
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        var scriptFile = "../../docs/ScriptTemplate.yaml";
        Script script = reader.read(new File(scriptFile));

        try (ScriptPackage scriptPackage = ioc.instance(ScriptPackage.class)) {
            PackageResource scriptResource = scriptPackage.getScript();
            try (OutputStream os = scriptResource.getOutputStream()) {
                ScriptWriter writer = ioc.instance(ScriptWriter.class);
                writer.write(script, os);
            }

            Path zip = Files.createTempFile("zacepco-", ".zip");
            try (OutputStream os = Files.newOutputStream(zip)) {
                scriptPackage.getPackageManager().write(os);
            }
        }

    }

}
