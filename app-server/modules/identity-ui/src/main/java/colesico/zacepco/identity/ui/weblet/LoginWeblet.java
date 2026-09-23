package colesico.zacepco.identity.ui.weblet;

import colesico.framework.http.HttpMethod;
import colesico.framework.httprouter.RequestMethod;
import colesico.framework.httprouter.Route;
import colesico.framework.jjwt.JwtLoginMessage;
import colesico.framework.jjwt.WebJwt;
import colesico.framework.service.ApplicationException;
import colesico.framework.service.ParamsBean;
import colesico.framework.teleapi.TeleResult;
import colesico.framework.telehttp.result.NavigationResult;
import colesico.framework.weblet.Weblet;
import colesico.framework.weblet.result.ViewResult;
import colesico.zacepco.common.ui.model.Notice;
import colesico.zacepco.identity.srv.dto.AuthUser;
import colesico.zacepco.identity.srv.service.AuthService;
import colesico.zacepco.identity.ui.dto.LoginForm;
import colesico.zacepco.identity.ui.t9n.LoginMessages;

import java.util.Map;

@Weblet
@Route("./login")
public class LoginWeblet {

    static final String LOGIN_VIEW = "$identity/ui/tmpl/Login";
    private final AuthService authService;
    private final WebJwt jwt;
    private final LoginMessages messages;

    public LoginWeblet(AuthService authService, WebJwt jwt, LoginMessages messages) {
        this.authService = authService;
        this.jwt = jwt;
        this.messages = messages;
    }

    public ViewResult index() {
        return ViewResult.view(LOGIN_VIEW).build();
    }

    @RequestMethod(HttpMethod.POST)
    public TeleResult signin(@ParamsBean LoginForm form) {
        var redirect = form.getRedirect();

        if (redirect == null || !redirect.startsWith("/")) {
            redirect = "/";
        }

        try {
            var user = authService.authenticate(new AuthUser(form.getUsername(), form.getPassword())).orElse(null);
            if (user == null) {
                form.setNotice(Notice.error(messages.invalidCredentials()));
                return ViewResult.view(LOGIN_VIEW).model(form).build();
            } else {
                jwt.authenticate(new JwtLoginMessage(user.id.toString(), Map.of()));
                return NavigationResult.redirect(redirect);
            }
        } catch (ApplicationException e) {
            form.setNotice(Notice.error(e));
            return ViewResult.view(LOGIN_VIEW).model(form).build();
        }
    }

}
