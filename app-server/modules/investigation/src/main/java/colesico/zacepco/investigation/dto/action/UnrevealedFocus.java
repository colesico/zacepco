package colesico.zacepco.investigation.dto.action;

import colesico.zacepco.investigation.dto.DetectiveFocus;

public record UnrevealedFocus(DetectiveFocus unrevealedFocus) implements LocationSearchResult {
    @Override
    public String code() {
        return "UNREVEALED_FOCUS";
    }
}
