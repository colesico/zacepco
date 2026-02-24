package colesico.zacepco.script.test;

import colesico.framework.ioc.Ioc;
import colesico.framework.ioc.IocBuilder;
import colesico.zacepco.script.pkg.*;
import colesico.zacepco.script.model.script.Script;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
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
            ScriptResource scriptResource = scriptPackage.getScript();
            ScriptWriter writer = ioc.instance(ScriptWriter.class);
            scriptResource.setScript(script,writer);

            Path zip = Files.createTempFile("zacepco-", ".zip");
            scriptPackage.getPackageManager().write(zip);
        }

    }

}
