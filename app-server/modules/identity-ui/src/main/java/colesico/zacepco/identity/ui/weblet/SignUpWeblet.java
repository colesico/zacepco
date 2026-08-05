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

    public ViewResponse index(String inviteCode) {
        return ViewResponse.view("$identity/ui/tmpl/signup/Request").build();
    }

    @RequestMethod(HttpMethod.POST)
    public ViewResponse register(@ParamsBean RegForm form) {
        try {
            regService.registerUser(new RegUser(form.username, form.password, form.inviteCode));
            return ViewResponse.view("$identity/ui/tmpl/signup/Accepted").build();
        } catch (ApplicationException e) {
            form.setNotice(Notice.error(e));
            return ViewResponse.view("$identity/ui/tmpl/signup/Request").model(form).build();
        }
    }


    // @Route("*")  - this is default route for "other" method name
    @PlainMethod
    public RedirectResponse other(/* String routeSuffix */) {
        return RedirectResponse.of(SignUpWeblet.class, "index");
    }

}
