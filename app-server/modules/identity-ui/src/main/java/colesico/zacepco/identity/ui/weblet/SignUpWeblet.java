package colesico.zacepco.identity.ui.weblet;

import colesico.framework.http.HttpMethod;
import colesico.framework.httprouter.RequestMethod;
import colesico.framework.httprouter.Route;
import colesico.framework.service.Aggregate;
import colesico.framework.service.PlainMethod;
import colesico.framework.telehttp.response.RedirectResponse;
import colesico.framework.weblet.Weblet;
import colesico.framework.weblet.response.ViewResponse;
import colesico.zacepco.identity.srv.model.Registration;
import colesico.zacepco.identity.srv.service.UserService;
import colesico.zacepco.ui.model.ViewModel;

@Weblet
@Route("./signup")
public class SignUpWeblet {

    private final UserService userService;

    public SignUpWeblet(UserService userService) {
        this.userService = userService;
    }

    public ViewResponse index() {
        return ViewResponse.view("$identity/ui/tmpl/signup/Request").build();
    }

    // @Route("*")  - this is default route for "other" method name
    @PlainMethod
    public RedirectResponse other(/* String routeSuffix */) {
        return RedirectResponse.of(SignUpWeblet.class, "index");
    }

    @RequestMethod(HttpMethod.POST)
    public ViewResponse request(@Aggregate SignUpForm form) {
        userService.createUser(new Registration(form.getUsername(), form.getPassword()));
        return null;
    }

    public static class SignUpForm extends ViewModel {
        String username;
        String password;

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
    }
}
