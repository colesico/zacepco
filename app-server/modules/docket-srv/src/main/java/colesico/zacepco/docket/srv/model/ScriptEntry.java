package colesico.zacepco.docket.srv.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.Composition;
import colesico.framework.jdbirec.Record;
import colesico.framework.jdbirec.mediators.DateTextMediator;
import colesico.zacepco.docket.srv.jdbi.ScriptStatusMediator;
import colesico.zacepco.script.model.scriptdoc.ScriptDocument;

import java.util.Date;

/**
 * Represents script entry in scripts catalog
 *
 * @see ScriptDocument
 */
@Record(table = "scripts")
public class ScriptEntry {

    /**
     * Entry Id
     */
    @Column
    private Long id;

    /**
     * User who created this entry
     */
    @Column
    private Long userId;

    /**
     * Script status
     */
    @Column(mediator = ScriptStatusMediator.class)
    private ScriptStatus status;

    /**
     * Entry creation date
     */
    @Column(mediator = DateTextMediator.class)
    private Date createdAt;

    /**
     * Script summary
     */
    @Composition(renaming = "@column")
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

    public ScriptStatus getStatus() {
        return status;
    }

    public void setStatus(ScriptStatus status) {
        this.status = status;
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
