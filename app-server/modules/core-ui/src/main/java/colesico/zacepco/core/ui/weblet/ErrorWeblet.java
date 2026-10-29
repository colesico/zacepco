package colesico.zacepco.core.ui.weblet;

import colesico.framework.weblet.Weblet;
import colesico.framework.weblet.result.ViewResult;

@Weblet
public class ErrorWeblet {

    public ViewResult error404() {
        return ViewResult.view("$core/ui/tmpl/error/404").status(404).build();
    }

    public ViewResult error500() {
        return ViewResult.view("$core/ui/tmpl/error/500").status(500).build();
    }
}
