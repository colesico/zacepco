package colesico.zacepco.investigation.model.results;

import colesico.zacepco.investigation.model.DetectiveFocus;

public final class UndiscoveredFocus extends InspectionResult {

    public final DetectiveFocus undiscovered;

    private UndiscoveredFocus(DetectiveFocus undiscovered) {
        this.undiscovered = undiscovered;
    }

    public static UndiscoveredFocus of(DetectiveFocus focus) {
        return new UndiscoveredFocus(focus);
    }
}
