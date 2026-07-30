package colesico.zacepco.identity.srv.service;

import colesico.zacepco.identity.srv.model.User;

public interface UserListener {
    void onCreateUser(User user);
}
