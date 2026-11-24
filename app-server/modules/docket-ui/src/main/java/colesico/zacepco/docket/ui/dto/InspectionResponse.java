package colesico.zacepco.docket.ui.dto;

import colesico.zacepco.investigation.model.results.InspectionResult;

import java.util.Locale;

public class InspectionResponse {

    public final String code;
    public final InspectionResult result;

    public InspectionResponse(InspectionResult result) {
        this.result = result;
        this.code = result.getClass().getSimpleName()
                .replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
                .replaceAll("([a-z\\d])([A-Z])", "$1_$2")
                .toUpperCase(Locale.ROOT);
    }
}
