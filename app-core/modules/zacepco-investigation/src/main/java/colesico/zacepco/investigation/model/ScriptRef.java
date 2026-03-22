package colesico.zacepco.investigation.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.Composition;
import colesico.framework.jdbirec.Record;

import java.util.Date;

/**
 * Script reference model
 */
@Record(table = "scripts")
public class ScriptRef {

    @Column
    private Long id;

    /**
     * User (owner) who created script reference and can manage it
     */
    @Column
    private Long userId;

    /**
     * Reference creation date
     */
    @Column
    private Date created;

    /**
     * Meta data reference
     */
    @Composition
    private ScriptMetaRef meta;

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


    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public ScriptMetaRef getMeta() {
        return meta;
    }

    public void setMeta(ScriptMetaRef meta) {
        this.meta = meta;
    }
}
