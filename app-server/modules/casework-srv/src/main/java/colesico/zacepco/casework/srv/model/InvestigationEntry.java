package colesico.zacepco.casework.srv.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.Record;
import colesico.framework.jdbirec.RecordView;
import colesico.framework.jdbirec.TagFilter;
import colesico.zacepco.casereg.srv.model.ScriptEntry;
import colesico.zacepco.casework.srv.jdbi.InvStateMediator;

import java.util.Date;

import static colesico.framework.jdbirec.RecordView.*;
import static colesico.framework.jdbirec.TagFilter.*;

/**
 * Investigation record
 */
@Record(table = "investigations",
        views = {
                @RecordView(name = VIEW_FULL, tagFilter = @TagFilter(anyOf = {TG_FULL, TF_NO_TAGS})),
                @RecordView(name = VIEW_BRIEF, tagFilter = @TagFilter(anyOf = {TG_BRIEF, TF_NO_TAGS}))
        }
)
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

    /**
     * When case entity was created
     */
    @Column
    private Date createdAt;


    @Column
    private String title;

    @Column(mediator = InvStateMediator.class, tags = TG_FULL)
    private InvestigationState state;

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public InvestigationState getState() {
        return state;
    }

    public void setState(InvestigationState state) {
        this.state = state;
    }
}
