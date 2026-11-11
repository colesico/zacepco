package colesico.zacepco.docket.srv.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.mediators.ListStringTextMediator;

import java.util.List;

public class ScriptSummary {

    @Column
    public String title;

    @Column(mediator = ListStringTextMediator.class)
    public List<String> authors;

    public ScriptSummary() {
    }

    public ScriptSummary(String title, List<String> authors) {
        this.title = title;
        this.authors = authors;
    }
}
