package colesico.zacepco.common.ui.restlet;

import colesico.framework.httprouter.Route;
import colesico.framework.restlet.Restlet;

@Restlet
@Route("./healthcheck")
public class Healthcheck {
    public String index(){
        return "OK";
    }
}
