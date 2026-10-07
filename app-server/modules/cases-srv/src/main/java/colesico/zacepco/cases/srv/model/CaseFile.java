package colesico.zacepco.cases.srv.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.Composition;
import colesico.framework.jdbirec.Record;
import colesico.framework.jdbirec.mediators.DateTextMediator;
import colesico.zacepco.cases.srv.jdbi.AccessTypeMediator;
import colesico.zacepco.script.model.script.Script;

import java.util.Date;

/**
 * Case file model
 *
 * @see Script
 */
@Record(table = "cases")
public class CaseFile {

    /**
     * Case file Id
     */
    @Column
    private Long id;

    /**
     * User who created this case file
     */
    @Column
    private Long userId;

    /**
     * Case file access
     */
    @Column(mediator = AccessTypeMediator.class)
    private CaseFileAccessType access;

    /**
     * Case file creation date
     */
    @Column(mediator = DateTextMediator.class)
    private Date createdAt;

    /**
     * Script reference
     */
    @Composition(renaming = "script_@column")
    private ScriptReference script;

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

    public CaseFileAccessType getAccess() {
        return access;
    }

    public void setAccess(CaseFileAccessType access) {
        this.access = access;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ScriptReference getScript() {
        return script;
    }

    public void setScript(ScriptReference script) {
        this.script = script;
    }
}
