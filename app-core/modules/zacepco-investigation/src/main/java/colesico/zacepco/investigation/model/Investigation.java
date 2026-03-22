package colesico.zacepco.investigation.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.Record;

import java.util.Date;

/**
 * Investigation model
 */
@Record(table = "investigations")
public class Investigation {

    /**
     * Investigation id
     */
    @Column
    private Long id;

    /**
     * User (owner) who created investigation and can manage it
     */
    @Column
    private Long userid;

    @Column
    private InvestigationStatus status;

    /**
     * Script ref
     *
     * @see ScriptRef
     */
    @Column
    private Long scriptId;

    /**
     * Script title
     */
    @Column
    private String scriptTitle;

    /**
     * When investigation entity was created
     */
    @Column
    private Date created;

    /**
     * Current amount of resource for investigation
     */
    @Column
    private Integer resource;

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

    public Integer getResource() {
        return resource;
    }

    public void setResource(Integer resource) {
        this.resource = resource;
    }

    public String getScriptTitle() {
        return scriptTitle;
    }

    public void setScriptTitle(String scriptTitle) {
        this.scriptTitle = scriptTitle;
    }
}
