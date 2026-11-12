package colesico.zacepco.docket.srv.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.mediators.ListStringTextMediator;

import java.util.List;

public class ScriptSummary {

    /**
     * Script uid
     * @see colesico.zacepco.script.model.scriptdoc.MetainfoSpec#uid
     */
    @Column
    public String uid;

    /**
     * @see colesico.zacepco.script.model.scriptdoc.MetainfoSpec#version
     */
    @Column
    public Integer version;

    @Column
    public String title;

    @Column(mediator = ListStringTextMediator.class)
    public List<String> authors;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
}
