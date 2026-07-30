package colesico.zacepco.identity.srv.service;

import colesico.framework.ioc.production.Polysupplier;
import colesico.framework.service.Service;
import colesico.framework.validation.Validator;
import colesico.zacepco.identity.srv.dao.UserDao;
import colesico.zacepco.identity.srv.model.Registration;
import colesico.zacepco.identity.srv.model.User;
import colesico.zacepco.identity.srv.v8n.UserValidatorBuilder;
import jakarta.inject.Provider;

import java.util.Date;
import java.util.Locale;


@Service
public class UserService {

    private final UserDao userDao;
    private final Validator<Registration> registrationV8n;
    private final Provider<Locale> locale;
    private final Polysupplier<UserListener> listeners;

    public UserService(UserDao userDao,
                       UserValidatorBuilder userValidatorBuilder,
                       Provider<Locale> locale,
                       Polysupplier<UserListener> listeners) {
        this.userDao = userDao;
        this.listeners = listeners;
        this.locale = locale;
        this.registrationV8n = userValidatorBuilder.registrationValidator();
    }

    public User createUser(Registration registration) {

        registrationV8n.accept(registration);

        var u = new User();
        u.setCreatedAt(new Date());
        u.setDisabled(false);
        u.setUsername(registration.getUsername());
        u.setLocale(locale.get());

        final var user = userDao.createUser(u);

        listeners.forEach(ul -> ul.onCreateUser(user));

        return user;
    }
}
