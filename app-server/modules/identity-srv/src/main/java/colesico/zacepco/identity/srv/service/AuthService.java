package colesico.zacepco.identity.srv.service;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.identity.srv.assist.HashUtils;
import colesico.zacepco.identity.srv.model.Auth;

@Service
@Transactional
public class AuthService {

    public Auth createAuth(Long userId, String password) {

        Auth auth = new Auth();

        auth.userId = userId;

        var ps = HashUtils.textToHashSalt(password);
        auth.passwordHash = ps[0];
        auth.salt = ps[1];

        auth.lastLoginAt = null;



    }
}
