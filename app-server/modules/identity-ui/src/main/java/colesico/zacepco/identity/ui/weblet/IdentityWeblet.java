package colesico.zacepco.identity.ui.weblet;

import colesico.framework.assist.StringUtils;
import colesico.framework.http.HttpMethod;
import colesico.framework.httprouter.RequestMethod;
import colesico.framework.httprouter.Route;
import colesico.framework.telehttp.response.DynamicResponse;
import colesico.framework.telehttp.response.RedirectResponse;
import colesico.framework.weblet.Weblet;
import colesico.framework.weblet.response.ViewResponse;

@Weblet
@Route("./")
public class IdentityWeblet {

    public ViewResponse login() {
        return ViewResponse.view("$identity/ui/tmpl/Login").build();
    }

    @RequestMethod(HttpMethod.POST)
    public DynamicResponse signin(String username, String password, String redirect) {
        // Do login
        if (redirect == null || !redirect.startsWith("/")) {
            redirect = "/";
        }
        return RedirectResponse.of(redirect).toDynamic();
    }

}
