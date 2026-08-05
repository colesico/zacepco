package colesico.zacepco.identity.srv.service;

import colesico.framework.ioc.listener.PostConstruct;
import colesico.framework.service.PlainMethod;
import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.common.srv.utils.Base58;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InitialService {
    public static final String INITIAL_USERNAME = "admin";
    public static final int INITIAL_PASSWORD_LEN = 6;
    public static final int INITIAL_INVITES_NUM = 15;
    public static final Period DEFAULT_INVITES_EXP_PERIOD = Period.ofYears(10);

    private final UserService userService;
    private final AuthService authService;
    private final InviteService inviteService;

    private final List<String> initialInviteCodes = new ArrayList<>();
    private String initialUserPassword;


    public InitialService(UserService userService, AuthService authService, InviteService inviteService) {
        this.userService = userService;
        this.authService = authService;
        this.inviteService = inviteService;
    }

    @PostConstruct
    public void init() {
        var defaultUser = userService.findUserByName(INITIAL_USERNAME);
        if (defaultUser.isEmpty()) {
            var user = userService.createUser(INITIAL_USERNAME);
            initialUserPassword = Base58.encode(inviteService.createRandomCode(INITIAL_PASSWORD_LEN));
            authService.createAuth(user.id, initialUserPassword);
            for (var i = 0; i < INITIAL_INVITES_NUM; i++) {
                var code = inviteService.createInvite(user.getId(), DEFAULT_INVITES_EXP_PERIOD);
                initialInviteCodes.add(code);
            }
        }
    }

    @PlainMethod
    public void cleanup(){
        initialInviteCodes.clear();
        initialUserPassword = null;
    }

    public List<String> initialInviteCodes() {
        return initialInviteCodes;
    }

    public String initialUserPassword() {
        return initialUserPassword;
    }

    public String initialUsername() {
        return INITIAL_USERNAME;
    }

}
