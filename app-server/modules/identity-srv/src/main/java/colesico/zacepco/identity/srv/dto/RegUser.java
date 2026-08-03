package colesico.zacepco.identity.srv.dto;

public class RegUser {

    public String username;
    public String password;
    public String inviteCode;

    public RegUser() {
    }

    public RegUser(String username, String password, String inviteCode) {
        this.username = username;
        this.password = password;
        this.inviteCode = inviteCode;
    }

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
