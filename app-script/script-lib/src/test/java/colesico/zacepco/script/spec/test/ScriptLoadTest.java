package colesico.zacepco.script.spec.test;

import colesico.framework.ioc.Ioc;
import colesico.framework.ioc.IocBuilder;
import colesico.zacepco.script.spec.assist.ScriptSerializer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ScriptLoadTest {

    private Ioc ioc;

    @BeforeClass
    public void init() {
        ioc = IocBuilder.create().build();
    }

    @Test
    public void testLoadScript() {
        ScriptSerializer serializer = ioc.instance(ScriptSerializer.class);
       // serializer.deserialize();
    }

}
