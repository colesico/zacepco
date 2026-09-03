package colesico.zacepco.identity.srv.service;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.identity.srv.assist.HashUtils;
import colesico.zacepco.identity.srv.dao.AuthDao;
import colesico.zacepco.identity.srv.model.Auth;
import colesico.zacepco.identity.srv.model.User;

import java.util.Optional;

@Service
@Transactional
public class AuthService {

    private final AuthDao authDao;
    private final UserService userService;

    public AuthService(AuthDao authDao, UserService userService) {
        this.authDao = authDao;
        this.userService = userService;
    }

    public Auth createAuth(Long userId, String password) {

        Auth auth = new Auth();

        auth.setUserId(userId);

        var ps = HashUtils.textToHashSaltStr(password);
        auth.setPasswordHash(ps[0]);
        auth.setSalt(ps[1]);

        auth.setLastLoginAt(null);

        return authDao.createAuth(auth);

    }

    public Optional<Auth> findAuthByUserId(Long userId) {
        return authDao.findAuthByUserId(userId);
    }

    public Optional<User> authenticate(String username, String password) {
        var user = userService.findUserByName(username).orElse(null);
        if (user == null) {
            return Optional.empty();
        }

        if (user.getDisabled()) {
            return Optional.empty();
        }

        var auth = authDao.findAuthByUserId(user.getId()).orElse(null);
        if (auth == null) {
            return Optional.empty();
        }

        var passwordHash = HashUtils.textToHashStr(password, auth.getSalt());

        if (!passwordHash.equals(auth.getPasswordHash())) {
            return Optional.empty();
        }

        return Optional.of(user);
    }
}
