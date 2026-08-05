package colesico.zacepco.identity.ui.weblet;

import colesico.framework.httprouter.Route;
import colesico.framework.telehttp.response.DynamicResponse;
import colesico.framework.telehttp.response.RedirectResponse;
import colesico.framework.telehttp.response.StringResponse;
import colesico.framework.weblet.Weblet;
import colesico.zacepco.identity.srv.service.InitialService;
import colesico.zacepco.identity.ui.t9n.InitialMessages;
import jakarta.inject.Provider;

import java.util.Locale;

@Weblet
@Route("./")
public class InitialWeblet {

    private final InitialService initialService;
    private final InitialMessages messages;

    public InitialWeblet(InitialService initialService, InitialMessages messages) {
        this.initialService = initialService;
        this.messages = messages;
    }

    public DynamicResponse index() {

        var codes = initialService.initialInviteCodes();

        if (codes.isEmpty()) {
            return RedirectResponse.of(LoginWeblet.class, "index").toDynamic();
        }

        var text = messages.credentials(
                initialService.initialUsername(),
                initialService.initialUserPassword(),
                String.join("\n", codes));

        initialService.cleanup();

        return StringResponse.text(text).build().toDynamic();
    }
}
