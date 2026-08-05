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


    private final UserService userService;
    private final AuthService authService;
    private final InviteService inviteService;

    private final Polysupplier<RegListener> listeners;

    private final Validator<RegUser> regUserValidator;

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
