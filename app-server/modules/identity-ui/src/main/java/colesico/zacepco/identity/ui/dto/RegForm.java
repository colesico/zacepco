package colesico.zacepco.identity.ui.dto;

import colesico.zacepco.common.ui.model.ViewModel;

public class RegForm extends ViewModel {

    public String username;
    public String password;
    public String inviteCode;

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

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }
}
