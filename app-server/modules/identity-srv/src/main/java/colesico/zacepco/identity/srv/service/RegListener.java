package colesico.zacepco.identity.srv.service;

import colesico.zacepco.identity.srv.model.User;

public interface RegListener {
    void onRegisterUser(User user);
}
