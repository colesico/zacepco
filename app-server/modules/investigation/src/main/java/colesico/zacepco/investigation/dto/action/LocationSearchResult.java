package colesico.zacepco.investigation.dto.action;


public sealed interface LocationSearchResult
        permits
        ClueUncover,
        CluesExhausted,
        InsufficientBudget,
        InvalidD12,
        UnrevealedFocus {
}
