package colesico.zacepco.identity.srv.service;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.framework.validation.Validator;
import colesico.zacepco.identity.srv.dto.CreateUser;
import colesico.zacepco.identity.srv.dto.RegUser;


@Service
@Transactional
public class RegService {

    private final UserService userService;
    private final InviteService inviteService;

    private final Validator<RegUser> regUserV8n =null;

    public RegService(UserService userService, InviteService inviteService) {
        this.userService = userService;
        this.inviteService = inviteService;
    }

    public void register(RegUser regUser){
        regUserV8n.accept(regUser);
        inviteService.checkCode(regUser.getInviteCode());
        var createUser = new CreateUser(regUser.getUsername(),regUser.getUsername());
    }


}
