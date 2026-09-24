package colesico.zacepco.catalog.ui.weblet;

import colesico.framework.httprouter.Route;
import colesico.framework.weblet.Weblet;
import colesico.framework.weblet.result.ViewResult;

@Weblet
@Route("./")
public class IndexWeblet {

    static final String INDEX_VIEW = "$catalog/ui/tmpl/JsApp";

    public ViewResult index() {
        return ViewResult.view(INDEX_VIEW).build();
    }
}
