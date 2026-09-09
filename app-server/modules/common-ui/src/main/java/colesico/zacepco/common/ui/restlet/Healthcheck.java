package colesico.zacepco.common.ui.restlet;

import colesico.framework.http.HttpMethod;
import colesico.framework.httprouter.RequestMethod;
import colesico.framework.httprouter.Route;
import colesico.framework.restlet.Restlet;

@Restlet
@Route("./healthcheck")
public class Healthcheck {

    @RequestMethod(HttpMethod.HEAD)
    public void index(){
    }

}
