package colesico.zacepco.script.spec.test;

import colesico.framework.ioc.Ioc;
import colesico.framework.ioc.IocBuilder;
import colesico.zacepco.script.spec.assist.ScriptReader;
import colesico.zacepco.script.spec.model.script.Script;
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
    }

}
