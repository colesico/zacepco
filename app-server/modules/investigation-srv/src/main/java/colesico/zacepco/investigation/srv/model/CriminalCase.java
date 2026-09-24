package colesico.zacepco.investigation.srv.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.Record;

import java.util.Date;

/**
 * Criminal case model
 */
@Record(table = "cases")
public class CriminalCase {

    /**
     * Criminal case id
     */
    @Column
    private Long id;

    /**
     * User (owner) who created case and can manage it
     */
    @Column
    private Long userid;

    @Column
    private CriminalCaseStatus status;

    /**
     * Current amount of resource for investigation
     */
    @Column
    private Integer resource;

    /**
     * Script ref
     *
     * @see ScriptEntry
     */
    @Column
    private Long scriptId;

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

    public CriminalCaseStatus getStatus() {
        return status;
    }

    public void setStatus(CriminalCaseStatus status) {
        this.status = status;
    }

    public Integer getResource() {
        return resource;
    }

    public void setResource(Integer resource) {
        this.resource = resource;
    }

    public Long getScriptId() {
        return scriptId;
    }

    public void setScriptId(Long scriptId) {
        this.scriptId = scriptId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
