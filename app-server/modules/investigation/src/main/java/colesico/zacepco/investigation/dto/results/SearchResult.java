package colesico.zacepco.investigation.dto.results;


public sealed interface SearchResult
        permits
        ClueDiscovered,
        CluesExhausted,
        FocusLimitExceeded,
        InsufficientBudget,
        InvalidD12,
        UndiscoveredFocus {
}
