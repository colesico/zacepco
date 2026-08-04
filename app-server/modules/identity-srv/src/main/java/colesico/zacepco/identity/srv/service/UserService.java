package colesico.zacepco.identity.srv.service;

import colesico.framework.ioc.listener.PostConstruct;
import colesico.framework.ioc.production.Polysupplier;
import colesico.framework.service.Service;
import colesico.framework.validation.Validator;
import colesico.zacepco.common.srv.service.AppListener;
import colesico.zacepco.identity.srv.dao.UserDao;
import colesico.zacepco.identity.srv.model.User;
import colesico.zacepco.identity.srv.validation.UserValidatorBuilder;
import jakarta.inject.Provider;

import java.util.Date;
import java.util.Locale;
import java.util.Optional;


@Service
public class UserService {

    private final UserDao userDao;
    private final Validator<String> createUserV8n;
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

    @PostConstruct
    public void init() {

    }

    public User createUser(String username) {

        createUserV8n.accept(username);

        var u = new User();
        u.setCreatedAt(new Date());
        u.setDisabled(false);
        u.setUsername(username);
        u.setLocale(locale.get());

        final var user = userDao.createUser(u);

        listeners.forEach(listener -> listener.onCreateUser(user));

        return user;
    }

    public Optional<User> findUserByName(String username) {
        return userDao.findUserByUsername(username);
    }

    public Optional<User> findUserById(Long id) {
        return userDao.findUserById(id);
    }


}
