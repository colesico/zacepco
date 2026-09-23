package colesico.zacepco.identity.ui.weblet;

import colesico.framework.httprouter.Route;

import colesico.framework.teleapi.TeleResult;
import colesico.framework.telehttp.result.NavigationResult;
import colesico.framework.telehttp.result.StringResult;
import colesico.framework.weblet.Weblet;
import colesico.zacepco.identity.srv.service.InitialService;
import colesico.zacepco.identity.ui.t9n.InitialMessages;

@Weblet
@Route("./")
public class InitialWeblet {

    private final InitialService initialService;
    private final InitialMessages messages;

    public InitialWeblet(InitialService initialService, InitialMessages messages) {
        this.initialService = initialService;
        this.messages = messages;
    }

    public TeleResult index() {

        var codes = initialService.initialInviteCodes();

        if (codes.isEmpty()) {
            return NavigationResult.redirect(LoginWeblet.class, "index");
        }

        var text = messages.credentials(
                initialService.initialUsername(),
                initialService.initialUserPassword(),
                String.join("\n", codes));

        initialService.cleanup();

        return StringResult.text(text).build();
    }
}
