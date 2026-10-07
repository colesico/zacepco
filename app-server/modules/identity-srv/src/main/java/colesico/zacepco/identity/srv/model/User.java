package colesico.zacepco.identity.srv.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.Record;
import colesico.framework.jdbirec.mediators.DateTextMediator;
import colesico.framework.jdbirec.mediators.LocaleTextMediator;

import java.util.Date;
import java.util.Locale;

@Record(table = "users")
public class User {

    @Column(exportable = false, insertAs = "@nop")
    public Long id;

    @Column
    public String username;

    @Column
    public Boolean disabled;

    @Column(mediator = LocaleTextMediator.class)
    public Locale locale;

    @Column(mediator = DateTextMediator.class)
    public Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
