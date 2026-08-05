package colesico.zacepco.identity.ui.weblet;

import colesico.framework.httprouter.Route;
import colesico.framework.weblet.Weblet;
import colesico.zacepco.identity.srv.service.RegService;
import colesico.zacepco.identity.ui.t9n.InitialMessages;

@Weblet
@Route("./")
public class InitialWeblet {

    private final RegService regService;
    private final InitialMessages messages;

    public InitialWeblet(RegService regService, InitialMessages messages) {
        this.regService = regService;
        this.messages = messages;
    }

    public String index() {
        var codes = regService.initialInviteCodes();

        if (codes.isEmpty()) {
            return messages.denied();
        }

        return messages.credentials(RegService.INITIAL_USERNAME,
                regService.initialUserPassword(),
                String.join("\n", codes));
    }
}
