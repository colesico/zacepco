package colesico.zacepco.investigation.dto.results;

import colesico.zacepco.investigation.dto.DetectiveFocus;

public final class UndiscoveredFocus
        extends InvestigationActionResult
        implements SearchResult {

    public DetectiveFocus focus;

    public UndiscoveredFocus(DetectiveFocus focus) {
        super("UNDISCOVERED_FOCUS");
        this.focus = focus;
    }
}
