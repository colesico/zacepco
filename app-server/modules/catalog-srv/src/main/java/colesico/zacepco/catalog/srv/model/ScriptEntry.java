package colesico.zacepco.catalog.srv.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.Composition;
import colesico.framework.jdbirec.Record;
import colesico.zacepco.catalog.srv.jdbi.AccessTypeMediator;
import colesico.zacepco.script.model.script.Script;
import colesico.zacepco.script.model.script.ScriptMetadata;

import java.time.LocalDate;
import java.util.Date;

/**
 * Script catalog entry
 *
 * @see Script
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
     * Script catalog status
     */
    @Column(mediator = AccessTypeMediator.class)
    private ScriptAccessType access;

    /**
     * Entry creation date
     */
    @Column
    private Date createdAt;

    @Composition(renaming = "script_@column")
    private ScriptInfo scriptInfo;

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

    public ScriptAccessType getAccess() {
        return access;
    }

    public void setAccess(ScriptAccessType access) {
        this.access = access;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ScriptInfo getScriptInfo() {
        return scriptInfo;
    }

    public void setScriptInfo(ScriptInfo scriptInfo) {
        this.scriptInfo = scriptInfo;
    }
}
