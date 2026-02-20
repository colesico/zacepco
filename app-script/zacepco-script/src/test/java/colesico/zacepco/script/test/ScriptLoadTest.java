package colesico.zacepco.script.test;

import colesico.framework.ioc.Ioc;
import colesico.framework.ioc.IocBuilder;
import colesico.zacepco.script.assist.ScriptReader;
import colesico.zacepco.script.assist.ScriptWriter;
import colesico.zacepco.script.model.script.Script;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class ScriptLoadTest {

    private Ioc ioc;

    @BeforeClass
    public void init() {
        ioc = IocBuilder.create().build();
    }

    @Test
    public void testLoadScript() {
        ScriptReader reader = ioc.instance(ScriptReader.class);
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        var scriptFile = "../../docs/ScriptTemplate.yaml";
        Script script = reader.read(new File(scriptFile));
        ScriptWriter writer = ioc.instance(ScriptWriter.class);
        System.out.println(writer.write(script));
    }

}
