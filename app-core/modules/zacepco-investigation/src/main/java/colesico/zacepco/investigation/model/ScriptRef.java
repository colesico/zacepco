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
     * Script uuid
     */
    @Column
    public String uuid;

    /**
     * Script title
     */
    @Column
    public String title;

    /**
     * Crime brief description
     */
    @Column
    public String annotation;

    /**
     * Reference creation date
     */
    @Column
    private Date created;

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
