package colesico.zacepco.docket.srv.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.Record;
import colesico.framework.jdbirec.RecordView;
import colesico.framework.jdbirec.TagFilter;
import colesico.framework.jdbirec.mediators.DateTextMediator;
import colesico.zacepco.docket.srv.jdbi.InvestigationStateMediator;
import colesico.zacepco.docket.srv.jdbi.InvestigationStatusMediator;
import colesico.zacepco.investigation.model.state.InvestigationState;

import java.util.Date;

import static colesico.framework.jdbirec.RecordView.*;
import static colesico.framework.jdbirec.TagFilter.*;

/**
 * Investigation record
 */
@Record(table = "investigations",
        views = {
                @RecordView(name = VIEW_FULL, tagFilter = @TagFilter(anyOf = {TAG_FULL, TF_NO_TAGS})),
                @RecordView(name = VIEW_BRIEF, tagFilter = @TagFilter(anyOf = {TAG_BRIEF, TF_NO_TAGS}))
        }
)
public class InvestigationEntry {

    /**
     * Investigation process id
     */
    @Column
    private Long id;

    /**
     * Script ref
     *
     * @see ScriptEntry
     */
    @Column
    private Long scriptEntryId;

    /**
     * User (owner) who created entry and can manage it
     */
    @Column
    private Long userId;

    @Column(mediator = InvestigationStatusMediator.class)
    private InvestigationStatus status;

    /**
     * When case entity was created
     */
    @Column(mediator = DateTextMediator.class)
    private Date createdAt;

    @Column(mediator = InvestigationStateMediator.class, tags = TAG_FULL)
    private InvestigationState state;

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

    public InvestigationState getState() {
        return state;
    }

    public void setState(InvestigationState state) {
        this.state = state;
    }
}
