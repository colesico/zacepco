package colesico.zacepco.identity.ui.weblet;

import colesico.framework.http.HttpMethod;
import colesico.framework.httprouter.RequestMethod;
import colesico.framework.httprouter.Route;
import colesico.framework.service.ApplicationException;
import colesico.framework.service.ParamsBean;
import colesico.framework.service.PlainMethod;
import colesico.framework.telehttp.result.NavigationResult;
import colesico.framework.weblet.Weblet;
import colesico.framework.weblet.result.ViewResult;
import colesico.zacepco.identity.srv.dto.RegUser;
import colesico.zacepco.identity.srv.service.RegService;
import colesico.zacepco.identity.ui.dto.RegForm;
import colesico.zacepco.common.ui.model.Notice;

@Weblet
@Route("./signup")
public class SignUpWeblet {

    private final RegService regService;

    public SignUpWeblet(RegService regService) {
        this.regService = regService;
    }

    public ViewResult index(String inviteCode) {
        return ViewResult.view("$identity/ui/tmpl/signup/Request").build();
    }

    @RequestMethod(HttpMethod.POST)
    public ViewResult register(@ParamsBean RegForm form) {
        try {
            regService.registerUser(new RegUser(form.username, form.password, form.inviteCode));
            return ViewResult.view("$identity/ui/tmpl/signup/Success").build();
        } catch (ApplicationException e) {
            form.setNotice(Notice.error(e));
            return ViewResult.view("$identity/ui/tmpl/signup/Request").model(form).build();
        }
    }


    // @Route("*")  - this is default route for "other" method name
    @PlainMethod
    public NavigationResult other(/* String routeSuffix */) {
        return NavigationResult.redirect(SignUpWeblet.class, "index");
    }

}
