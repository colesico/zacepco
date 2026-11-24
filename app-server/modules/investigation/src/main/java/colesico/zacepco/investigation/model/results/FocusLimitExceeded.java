package colesico.zacepco.investigation.model.results;

public final class FocusLimitExceeded extends InspectionResult {

    public final int focusLimit;

    private FocusLimitExceeded(int focusLimit) {
        this.focusLimit = focusLimit;
    }

    public static FocusLimitExceeded of(int focusLimit) {
        return new FocusLimitExceeded(focusLimit);
    }
}
