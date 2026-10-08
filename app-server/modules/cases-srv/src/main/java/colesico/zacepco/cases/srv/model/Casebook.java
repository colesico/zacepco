package colesico.zacepco.cases.srv.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.Composition;
import colesico.framework.jdbirec.Record;
import colesico.framework.jdbirec.mediators.DateTextMediator;
import colesico.zacepco.cases.srv.jdbi.AccessTypeMediator;
import colesico.zacepco.script.model.script.Script;

import java.util.Date;

/**
 * Casebook model
 *
 * @see Script
 */
@Record(table = "casebooks")
public class Casebook {

    /**
     * Case record Id
     */
    @Column
    private Long id;

    /**
     * User who created this case record
     */
    @Column
    private Long userId;

    /**
     * Case record access
     */
    @Column(mediator = AccessTypeMediator.class)
    private CasebookAccessType access;

    /**
     * Case record creation date
     */
    @Column(mediator = DateTextMediator.class)
    private Date createdAt;

    /**
     * Script summary
     */
    @Composition(renaming = "script_@column")
    private ScriptSummary summary;

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

    public CasebookAccessType getAccess() {
        return access;
    }

    public void setAccess(CasebookAccessType access) {
        this.access = access;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ScriptSummary getSummary() {
        return summary;
    }

    public void setSummary(ScriptSummary summary) {
        this.summary = summary;
    }
}
