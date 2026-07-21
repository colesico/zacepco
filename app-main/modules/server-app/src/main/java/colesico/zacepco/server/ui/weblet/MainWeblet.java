package colesico.zacepco.server.ui.weblet;

import colesico.framework.httprouter.Route;
import colesico.framework.weblet.Weblet;
import colesico.framework.weblet.response.ViewResponse;

@Weblet
@Route("/")
public class MainWeblet {

    public ViewResponse index() {
        return ViewResponse.view("$mainUi/Main.html").build();
    }

    /**
     * For docker healthcheck
     */
    public String status() {
        return "OK";
    }

}
