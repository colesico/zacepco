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
import colesico.zacepco.identity.srv.service.UserService;
import colesico.zacepco.identity.ui.dto.RegForm;
import colesico.zacepco.common.ui.model.Notice;

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

}
