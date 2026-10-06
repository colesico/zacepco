package colesico.zacepco.cases.srv.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.mediators.StringListMediator;
import colesico.zacepco.script.model.script.ScriptMetadata;

import java.time.LocalDate;
import java.util.List;

public class ScriptReference {

    /**
     * Script ID {@link ScriptMetadata#getId()}
     */
    @Column
    public String id;

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

    @Column(mediator = StringListMediator.class)
    public List<String> authors;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
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
