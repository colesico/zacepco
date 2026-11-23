package colesico.zacepco.investigation.dto.results;

public final class FocusLimitExceeded
        extends InvestigationActionResult
        implements SearchResult {

    public int focusLimit;

    public FocusLimitExceeded(int focusLimit) {
        super("FOCUS_LIMIT_EXCEEDEED");
        this.focusLimit = focusLimit;
    }

    public int focusLimit() {
        return focusLimit;
    }

}
