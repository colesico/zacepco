package colesico.zacepco.investigation.ui.weblet;

import colesico.framework.httprouter.Route;
import colesico.framework.weblet.Weblet;
import colesico.framework.weblet.result.ViewResult;

@Weblet
@Route("./")
public class IndexWeblet {

    static final String LOGIN_VIEW = "$investigation/ui/tmpl/JsApp";

    public ViewResult index() {
        return ViewResult.view(LOGIN_VIEW).build();
    }
}
