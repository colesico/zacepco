package colesico.zacepco.identity.srv.service;

import colesico.framework.ioc.production.Polysupplier;
import colesico.framework.service.Service;
import colesico.framework.validation.Validator;
import colesico.zacepco.identity.srv.dao.UserDao;
import colesico.zacepco.identity.srv.dto.CreateUser;
import colesico.zacepco.identity.srv.model.User;
import colesico.zacepco.identity.srv.v8n.UserValidatorBuilder;
import jakarta.inject.Provider;

import java.util.Date;
import java.util.Locale;
import java.util.Optional;


@Service
public class UserService {

    private final UserDao userDao;
    private final Validator<CreateUser> createUserV8n;
    private final Provider<Locale> locale;
    private final Polysupplier<UserListener> listeners;

    public UserService(UserDao userDao,
                       UserValidatorBuilder userValidatorBuilder,
                       Provider<Locale> locale,
                       Polysupplier<UserListener> listeners) {
        this.userDao = userDao;
        this.listeners = listeners;
        this.locale = locale;
        this.createUserV8n = userValidatorBuilder.createUserValidator();
    }


    public User createUser(String username) {

        createUserV8n.accept(createUser);

        var u = new User();
        u.setCreatedAt(new Date());
        u.setDisabled(false);
        u.setUsername(username);
        u.setLocale(locale.get());

        final var user = userDao.createUser(u);

        listeners.forEach(ul -> ul.onCreateUser(user));

        return user;
    }

    public Optional<User> findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    public Optional<User> findUserById(Long id) {
        return userDao.findUserById(id);
    }

}
