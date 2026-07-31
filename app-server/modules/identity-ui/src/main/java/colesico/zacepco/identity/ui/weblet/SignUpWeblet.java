package colesico.zacepco.identity.ui.weblet;

import colesico.framework.http.HttpMethod;
import colesico.framework.httprouter.RequestMethod;
import colesico.framework.httprouter.Route;
import colesico.framework.service.ApplicationException;
import colesico.framework.service.ParamsBean;
import colesico.framework.service.PlainMethod;
import colesico.framework.telehttp.response.RedirectResponse;
import colesico.framework.weblet.Weblet;
import colesico.framework.weblet.response.ViewResponse;
import colesico.zacepco.identity.srv.dto.CreateUser;
import colesico.zacepco.identity.srv.service.UserService;
import colesico.zacepco.ui.model.Notice;
import colesico.zacepco.ui.model.ViewModel;

@Weblet
@Route("./signup")
public class SignUpWeblet {

    private final UserService userService;

    public SignUpWeblet(UserService userService) {
        this.userService = userService;
    }

    @Route("./:inviteCode")
    public ViewResponse index(String inviteCode) {
        return ViewResponse.view("$identity/ui/tmpl/signup/Request").build();
    }

    // @Route("*")  - this is default route for "other" method name
    @PlainMethod
    public RedirectResponse other(/* String routeSuffix */) {
        return RedirectResponse.of(SignUpWeblet.class, "index");
    }

    @RequestMethod(HttpMethod.POST)
    public ViewResponse register(@ParamsBean RegForm form) {
        try {
            userService.createUser(new CreateUser(form.username, form.password));
            return ViewResponse.view("$identity/ui/tmpl/signup/Accepted").build();
        } catch (ApplicationException e) {
            form.setNotice(Notice.error(e));
            return ViewResponse.view("$identity/ui/tmpl/signup/Request").model(form).build();
        }
    }



    public static class RegForm extends ViewModel {

        String username;
        String password;
        String inviteCode;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }
    }
}
