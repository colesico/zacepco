package colesico.zacepco.investigation.model.results;

public final class FocusLimitExceeded extends InspectionResult {

    public final int itemsCount;
    public final int focusLimit;

    public FocusLimitExceeded(int itemsCount, int focusLimit) {
        this.itemsCount = itemsCount;
        this.focusLimit = focusLimit;
    }

    public static FocusLimitExceeded of(int itemsCount, int focusLimit) {
        return new FocusLimitExceeded(itemsCount, focusLimit);
    }
}
