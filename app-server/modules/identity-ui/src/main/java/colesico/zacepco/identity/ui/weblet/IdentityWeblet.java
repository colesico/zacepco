package colesico.zacepco.identity.ui.weblet;

import colesico.framework.httprouter.Route;
import colesico.framework.weblet.Weblet;
import colesico.framework.weblet.response.ViewResponse;

@Weblet
@Route("./")
public class IdentityWeblet {

    public ViewResponse login(){
        return ViewResponse.view("$identity/ui/tmpl/Login").build();
    }
}
