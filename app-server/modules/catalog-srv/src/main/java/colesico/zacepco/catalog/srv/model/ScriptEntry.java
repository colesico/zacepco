package colesico.zacepco.catalog.srv.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.Record;
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
    @Column
    private ScriptAccessType access;

    /**
     * Entry creation date
     */
    @Column
    private Date createdAt;

    /**
     * Script ID {@link ScriptMetadata#getId()}
     */
    @Column
    public String sid;

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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
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
