package colesico.zacepco.casework.srv.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.Record;
import colesico.zacepco.casereg.srv.model.ScriptEntry;

import java.util.Date;

/**
 * Investigation process model
 */
@Record(table = "investigations")
public class InvestigationEntry {

    /**
     * Process id
     */
    @Column
    private Long id;

    /**
     * User (owner) who created case and can manage it
     */
    @Column
    private Long userid;

    @Column
    private InvestigationStatus status;

    /**
     * Script ref
     *
     * @see ScriptEntry
     */
    @Column
    private Long scriptEntryId;

    @Column
    private String title;

    /**
     * When case entity was created
     */
    @Column
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public InvestigationStatus getStatus() {
        return status;
    }

    public void setStatus(InvestigationStatus status) {
        this.status = status;
    }

    public Long getScriptEntryId() {
        return scriptEntryId;
    }

    public void setScriptEntryId(Long scriptEntryId) {
        this.scriptEntryId = scriptEntryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
