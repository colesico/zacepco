package colesico.zacepco.inquiry.srv.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.Record;

import java.time.LocalDate;
import java.util.Date;

/**
 * Script reference
 */
@Record(table = "scripts")
public class ScriptRef {

    /**
     * Reference Id
     */
    @Column
    private Long id;

    /**
     * User who created  reference and can manage it (owner)
     */
    @Column
    private Long userId;

    /**
     * Reference creation date
     */
    @Column
    private Date createdAt;

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

    @Column
    public String[] authors;

    /**
     * Script version
     */
    @Column
    public Integer version;

    /**
     * Script creation date
     */
    @Column
    private LocalDate creationDate;

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
