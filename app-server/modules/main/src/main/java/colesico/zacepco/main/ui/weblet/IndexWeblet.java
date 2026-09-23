package colesico.zacepco.main.ui.weblet;

import colesico.framework.httprouter.Route;
import colesico.framework.weblet.Weblet;
import colesico.framework.weblet.result.ViewResult;

@Weblet
@Route("/")
public class IndexWeblet {

    public ViewResult index() {
        return ViewResult.view("$main/ui/tmpl/Index.html").build();
    }

    /**
     * For docker healthcheck
     */
    public String status() {
        return "OK";
    }

}
