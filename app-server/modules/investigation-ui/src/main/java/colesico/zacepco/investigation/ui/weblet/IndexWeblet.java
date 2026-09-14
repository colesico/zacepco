package colesico.zacepco.investigation.ui.weblet;

import colesico.framework.httprouter.Route;
import colesico.framework.weblet.Weblet;
import colesico.framework.weblet.response.ViewResponse;

@Weblet
@Route("./")
public class IndexWeblet {

    static final String LOGIN_VIEW = "$investigation/ui/tmpl/JsApp";

    public ViewResponse index() {
        return ViewResponse.view(LOGIN_VIEW).build();
    }
}
