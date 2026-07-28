package colesico.zacepco.identity.ui.weblet;

import colesico.framework.http.HttpMethod;
import colesico.framework.httprouter.RequestMethod;
import colesico.framework.httprouter.Route;
import colesico.framework.service.PlainMethod;
import colesico.framework.telehttp.response.RedirectResponse;
import colesico.framework.weblet.Weblet;
import colesico.framework.weblet.response.ViewResponse;

@Weblet
@Route("./signup")
public class SignUpWeblet {

    public ViewResponse index() {
        return ViewResponse.view("$identity/ui/tmpl/signup/Request").build();
    }

    // @Route("*")  - this is default route for "other" method name
    @PlainMethod
    public RedirectResponse other(/* String routeSuffix */) {
        return RedirectResponse.of(SignUpWeblet.class, "index");
    }

    @RequestMethod(HttpMethod.POST)
    public ViewResponse request(String username, String password, String code) {
        return null;
    }


}
