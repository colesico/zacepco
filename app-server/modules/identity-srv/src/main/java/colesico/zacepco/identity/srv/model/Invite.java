package colesico.zacepco.identity.srv.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.Record;

import java.util.Date;

@Record(table = "invites")
public class Invite {

    /**
     * Invite id
     */
    @Column
    public Long id;

    /**
     * The existing user who  owns invite code.
     */
    @Column
    public Long userId;

    /**
     * Hashed invite code
     */
    @Column
    public String codeHash;

    /**
     *  The newly registered person who used the code to join
     */
    @Column
    public Long inviteeId;

    @Column
    public Date createdAt;

    @Column
    public Date expiredAt;

    @Column
    public Date commitedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCodeHash() {
        return codeHash;
    }

    public void setCodeHash(String codeHash) {
        this.codeHash = codeHash;
    }

    public Long getInviteeId() {
        return inviteeId;
    }

    public void setInviteeId(Long inviteeId) {
        this.inviteeId = inviteeId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    public Date getCommitedAt() {
        return commitedAt;
    }

    public void setCommitedAt(Date commitedAt) {
        this.commitedAt = commitedAt;
    }
}
