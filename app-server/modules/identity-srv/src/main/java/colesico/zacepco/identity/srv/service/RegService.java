package colesico.zacepco.identity.srv.service;

import colesico.framework.service.ApplicationException;
import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.framework.validation.Validator;
import colesico.zacepco.identity.srv.dto.CreateUser;
import colesico.zacepco.identity.srv.dto.RegUser;
import colesico.zacepco.identity.srv.model.User;
import colesico.zacepco.identity.srv.v8n.RegValidatorBuilder;


@Service
@Transactional
public class RegService {

    private final UserService userService;
    private final InviteService inviteService;
    private final AuthService authService;

    private final Validator<RegUser> regUserValidator;

    public RegService(UserService userService,
                      InviteService inviteService,
                      AuthService authService,
                      RegValidatorBuilder regValidatorBuilder) {
        this.userService = userService;
        this.inviteService = inviteService;
        this.authService = authService;
        this.regUserValidator = regValidatorBuilder.createRegUserValidator(
                ctx -> userService.findUserByUsername(ctx.value()).isEmpty(),
                ctx -> inviteService.findValidInvite(ctx.value()) == InviteService.CheckCode.OK
        );
    }


    public User registerUser(RegUser regUser) {
        regUserValidator.accept(regUser);
        var user = userService.createUser(regUser.username);
        authService.createAuth(user.id, regUser.password);
        if (!inviteService.commitInvite(regUser.inviteCode, user.id)) {
            throw new ApplicationException("Unable to commit invite");
        }
        return user;
    }


}
