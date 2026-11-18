package colesico.zacepco.investigation.dto.action;


public sealed interface LocationSearchResult extends InvestigationActionResult
        permits
        ClueUncover,
        CluesExhausted,
        InsufficientBudget,
        InvalidD12,
        UnrevealedFocus {
}
