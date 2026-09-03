package colesico.zacepco.identity.srv.service;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.framework.validation.Validator;
import colesico.zacepco.identity.srv.assist.HashUtils;
import colesico.zacepco.identity.srv.dao.AuthDao;
import colesico.zacepco.identity.srv.dto.AuthUser;
import colesico.zacepco.identity.srv.dto.RegUser;
import colesico.zacepco.identity.srv.model.Auth;
import colesico.zacepco.identity.srv.model.User;
import colesico.zacepco.identity.srv.validation.AuthValidatorBuilder;
import colesico.zacepco.identity.srv.validation.RegValidatorBuilder;

import java.util.Optional;

@Service
@Transactional
public class AuthService {

    private final AuthDao authDao;
    private final UserService userService;

    private final Validator<AuthUser> authUserValidator;

    public AuthService(AuthDao authDao, UserService userService, AuthValidatorBuilder authValidatorBuilder) {
        this.authDao = authDao;
        this.userService = userService;
        this.authUserValidator = authValidatorBuilder.createAuthUserValidator();
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

    public Optional<User> authenticate(AuthUser claimant) {

        authUserValidator.accept(claimant);

        var user = userService.findUserByName(claimant.getUsername()).orElse(null);
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

        var passwordHash = HashUtils.textToHashStr(claimant.getPassword(), auth.getSalt());

        if (!passwordHash.equals(auth.getPasswordHash())) {
            return Optional.empty();
        }

        return Optional.of(user);
    }
}
