package colesico.zacepco.ui.weblet;

import colesico.framework.weblet.Weblet;
import colesico.framework.weblet.response.ViewResponse;

@Weblet
public class ErrorWeblet {

    public ViewResponse error404() {
        return ViewResponse.view("$ui/tmpl/error/404").statusCode(404).build();
    }

    public ViewResponse error500() {
        return ViewResponse.view("$ui/tmpl/error/500").statusCode(500).build();
    }
}
