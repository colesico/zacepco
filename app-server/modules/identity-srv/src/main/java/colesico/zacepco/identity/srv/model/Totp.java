package colesico.zacepco.identity.srv.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.Record;

import java.util.Date;

/**
 * TOTP (RFC 6238) authentication
 */
@Record(table = "totp")
public class Totp {

    @Column
    public Long userId;

    @Column
    public String key;

    @Column
    public Date lastLoginAt;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(Date lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }
}
