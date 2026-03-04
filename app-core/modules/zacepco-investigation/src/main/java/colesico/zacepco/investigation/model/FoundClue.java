package colesico.zacepco.investigation.model;

import colesico.zacepco.script.model.entity.ClueId;

/**
 * Clue, opened by location search, personage interrogation, expertise
 */
public class FoundClue {

    /**
     * Investigation ref
     */
    private Long investigationId;

    /**
     * Type of investigative action during which the evidence was obtained
     */
    private InvestigationActionType action;

    /**
     * Source where clue was found (e.g. location id, personage id, null for expertise)
     */
    private Long sourceId;

    /**
     * Clue ref
     */
    private ClueId clueId;

    /**
     * Who found the clue
     */
    private Long detectiveId;

    public Long getInvestigationId() {
        return investigationId;
    }

    public void setInvestigationId(Long investigationId) {
        this.investigationId = investigationId;
    }

    public InvestigationActionType getAction() {
        return action;
    }

    public void setAction(InvestigationActionType action) {
        this.action = action;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public ClueId getClueId() {
        return clueId;
    }

    public void setClueId(ClueId clueId) {
        this.clueId = clueId;
    }

    public Long getDetectiveId() {
        return detectiveId;
    }

    public void setDetectiveId(Long detectiveId) {
        this.detectiveId = detectiveId;
    }
}
