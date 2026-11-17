package colesico.zacepco.investigation.dto;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;
import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.Set;

abstract public class InvestigationActionResult {
    public ClueSpec clue;
    public Set<TId> matchedIds;
    public Set<TId.Type> matchedTypes;
    public Set<TId.Type> unmatchedTypes;
    public boolean lastClue;

    public InvestigationActionResult() {
    }

    public InvestigationActionResult(ClueSpec clue, Set<TId> matchedIds, Set<TId.Type> matchedTypes, Set<TId.Type> unmatchedTypes, boolean lastClue) {
        this.clue = clue;
        this.matchedIds = matchedIds;
        this.matchedTypes = matchedTypes;
        this.unmatchedTypes = unmatchedTypes;
        this.lastClue = lastClue;
    }
}
