package colesico.zacepco.docket.ui.dto;

import colesico.zacepco.investigation.dto.DetectiveFocus;
import colesico.zacepco.investigation.dto.action.LocationSearchResult;

public class LocationSearchResponse {
    public DetectiveFocus focus;
    public int d12;

    public final String code;
    public final LocationSearchResult details;

    public int budget;

    public LocationSearchResponse(DetectiveFocus focus, int d12, LocationSearchResult details, int budget) {
        this.focus = focus;
        this.d12 = d12;
        this.code = details.code();
        this.details = details;
        this.budget = budget;
    }
}
