package colesico.zacepco.investigation.dto;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;
import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.Set;

import static colesico.zacepco.investigation.dto.InvestigationActionResult.Status.OK;

abstract public class InvestigationActionResult {

    public final Status status;

    public ClueSpec clue;
    public Set<TId> matchedIds;
    public Set<TId.Type> matchedTypes;
    public Set<TId.Type> unmatchedTypes;

    public int cluesRemaining;

    /**
     * Points awarded to the detective
     */
    public int awardedPoints = 0;

    public InvestigationActionResult(Status status) {
        this.status = status;
    }

    public boolean isOK() {
        return OK.equals(status);
    }

    public enum Status {
        OK,
        INSUFFICIENT_BUDGET,
        INVALID_FOCUS,
        CLUES_EXHAUSTED,
        INVALID_D12_VALUE
    }
}
