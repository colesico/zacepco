package colesico.zacepco.identity.srv.service;

import colesico.framework.ioc.listener.PostConstruct;
import colesico.framework.ioc.production.Polysupplier;
import colesico.framework.service.ApplicationException;
import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.framework.validation.Validator;
import colesico.zacepco.common.srv.utils.Base32;
import colesico.zacepco.common.srv.utils.Base58;
import colesico.zacepco.identity.srv.dto.RegUser;
import colesico.zacepco.identity.srv.model.User;
import colesico.zacepco.identity.srv.validation.RegValidatorBuilder;

import java.time.Period;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Service
@Transactional
public class RegService {

    public static final String INITIAL_USERNAME = "admin";

    public static final int INITIAL_PASSWORD_LEN = 6;
    public static final int INITIAL_INVITES_NUM = 15;

    public static final Period DEFAULT_INVITES_EXP_PERIOD = Period.ofYears(10);

    private final UserService userService;
    private final AuthService authService;
    private final InviteService inviteService;

    private final Polysupplier<RegListener> listeners;

    private final Validator<RegUser> regUserValidator;

    private final List<String> initialInviteCodes = new ArrayList<>();
    private String initialUserPassword;

    public RegService(UserService userService,
                      AuthService authService,
                      InviteService inviteService,
                      Polysupplier<RegListener> listeners,
                      RegValidatorBuilder regValidatorBuilder) {
        this.userService = userService;
        this.inviteService = inviteService;
        this.authService = authService;
        this.listeners = listeners;

        this.regUserValidator = regValidatorBuilder.createRegUserValidator(
                ctx -> userService.findUserByName(ctx.value()).isEmpty(),
                ctx -> inviteService.findOpenInvite(ctx.value()).isPresent()
        );
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

    public List<String> initialInviteCodes() {
        var result = new ArrayList<>(initialInviteCodes);
        initialInviteCodes.clear();
        return result;
    }

    public String initialUserPassword() {
        var p = initialUserPassword;
        initialUserPassword = null;
        return p;
    }

    public User registerUser(RegUser regUser) {
        regUserValidator.accept(regUser);

        var user = userService.createUser(regUser.username);

        authService.createAuth(user.id, regUser.password);

        if (!inviteService.commitInvite(regUser.inviteCode, user.id)) {
            throw new ApplicationException("Unable to commit invite");
        }

        listeners.forEach(listener -> listener.onRegisterUser(user));

        return user;
    }


}
