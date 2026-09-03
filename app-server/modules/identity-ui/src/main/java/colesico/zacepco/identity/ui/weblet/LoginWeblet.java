package colesico.zacepco.identity.ui.weblet;

import colesico.framework.http.HttpMethod;
import colesico.framework.httprouter.RequestMethod;
import colesico.framework.httprouter.Route;
import colesico.framework.jjwt.JwtLoginMessage;
import colesico.framework.jjwt.WebJwt;
import colesico.framework.service.ParamsBean;
import colesico.framework.telehttp.response.DynamicResponse;
import colesico.framework.telehttp.response.RedirectResponse;
import colesico.framework.weblet.Weblet;
import colesico.framework.weblet.response.ViewResponse;
import colesico.zacepco.common.ui.model.Notice;
import colesico.zacepco.identity.srv.service.AuthService;
import colesico.zacepco.identity.ui.dto.LoginForm;
import colesico.zacepco.identity.ui.t9n.LoginMessages;

import java.util.Map;

@Weblet
@Route("./login")
public class LoginWeblet {

    private final AuthService authService;
    private final WebJwt jwt;
    private final LoginMessages messages;

    public LoginWeblet(AuthService authService, WebJwt jwt, LoginMessages messages) {
        this.authService = authService;
        this.jwt = jwt;
        this.messages = messages;
    }

    public ViewResponse index() {
        return ViewResponse.view("$identity/ui/tmpl/Login").build();
    }

    @RequestMethod(HttpMethod.POST)
    public DynamicResponse signin(@ParamsBean LoginForm form) {
        var redirect = form.getRedirect();

        if (redirect == null || !redirect.startsWith("/")) {
            redirect = "/";
        }

        var user = authService.authenticate(form.getUsername(), form.getPassword()).orElse(null);
        if (user == null) {
            form.setNotice(Notice.error(messages.invalidCredentials()));
            return ViewResponse.view("$identity/ui/tmpl/Login").model(form).build().toDynamic();
        } else {
            jwt.authenticate(new JwtLoginMessage(user.id.toString(), Map.of()));
            return RedirectResponse.of(redirect).toDynamic();
        }
    }

}
