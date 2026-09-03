package colesico.zacepco.identity.ui.dto;

import colesico.zacepco.common.ui.model.ViewModel;

public class LoginForm extends ViewModel {
    public String username;
    public String password;
    public String redirect;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}
