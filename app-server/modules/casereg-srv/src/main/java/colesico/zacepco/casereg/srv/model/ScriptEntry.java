package colesico.zacepco.casereg.srv.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.Composition;
import colesico.framework.jdbirec.Record;
import colesico.framework.jdbirec.mediators.DateTextMediator;
import colesico.zacepco.casereg.srv.jdbi.AccessTypeMediator;
import colesico.zacepco.script.model.script.Script;

import java.util.Date;

/**
 * Script entry model
 *
 * @see Script
 */
@Record(table = "scripts")
public class ScriptEntry {

    @Column
    private Long id;

    /**
     * User who created this entry
     */
    @Column
    private Long userId;

    /**
     * Entry access
     */
    @Column(mediator = AccessTypeMediator.class)
    private ScriptEntryAccessType access;

    /**
     * Creation date
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

    public ScriptEntryAccessType getAccess() {
        return access;
    }

    public void setAccess(ScriptEntryAccessType access) {
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
