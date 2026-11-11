package colesico.zacepco.docket.srv.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.Composition;
import colesico.framework.jdbirec.Record;
import colesico.framework.jdbirec.mediators.DateTextMediator;
import colesico.zacepco.docket.srv.jdbi.AccessTypeMediator;
import colesico.zacepco.script.model.scriptdoc.ScriptDocument;

import java.util.Date;

/**
 * Script entry model
 *
 * @see ScriptDocument
 */
@Record(table = "scripts")
public class ScriptEntry {

    /**
     * Script id (PK)
     * @see ScriptDocument#metainfo#id
     */
    @Column
    private String id;

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
    @Composition(renaming = "@column")
    private ScriptSummary summary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
