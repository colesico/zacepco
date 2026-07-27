package colesico.zacepco.identity.ui.weblet;

import colesico.framework.weblet.Weblet;
import colesico.framework.weblet.response.ViewResponse;

@Weblet
public class IdentityWeblet {

    public ViewResponse login(){
        return ViewResponse.view("$identity/ui/tmpl/Login").build();
    }
}
