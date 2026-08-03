package colesico.zacepco.identity.srv.service;

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

    private final Validator<RegUser> regUserValidator;

    public RegService(UserService userService,
                      InviteService inviteService,
                      RegValidatorBuilder regValidatorBuilder) {
        this.userService = userService;
        this.inviteService = inviteService;
        this.regUserValidator = regValidatorBuilder.createRegUserValidator(
                ctx -> userService.findUserByUsername(ctx.value()).isEmpty(),
                ctx -> inviteService.checkCode(ctx.value()) == InviteService.CheckCode.OK
        );
    }


    public User registerUser(RegUser regUser) {
        regUserValidator.accept(regUser);
        var createUser = new CreateUser(regUser.getUsername(), regUser.getUsername());
        var user = userService.createUser(createUser);

    }


}
