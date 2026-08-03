package colesico.zacepco.identity.srv.service;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.identity.srv.assist.HashUtils;
import colesico.zacepco.identity.srv.dao.AuthDao;
import colesico.zacepco.identity.srv.model.Auth;

@Service
@Transactional
public class AuthService {

    private final AuthDao authDao;

    public AuthService(AuthDao authDao) {
        this.authDao = authDao;
    }

    public Auth createAuth(Long userId, String password) {

        Auth auth = new Auth();

        auth.setUserId(userId);

        var ps = HashUtils.textToHashSalt(password);
        auth.setPasswordHash(ps[0]);
        auth.setSalt(ps[1]);

        auth.setLastLoginAt(null);

        return authDao.createAuth(auth);

    }
}
