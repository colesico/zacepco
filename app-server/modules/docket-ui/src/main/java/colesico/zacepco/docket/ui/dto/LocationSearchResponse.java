package colesico.zacepco.docket.ui.dto;

import colesico.zacepco.investigation.dto.DetectiveFocus;
import colesico.zacepco.investigation.dto.action.LocationSearchResult;

public class LocationSearchResponse {
    public final String code;
    public final LocationSearchResult details;
    public DetectiveFocus focus;
    public int d12;

    public LocationSearchResponse(LocationSearchResult details, DetectiveFocus focus, int d12) {
        this.details = details;
        this.d12 = d12;
        this.focus = focus;
        this.code = details.code();
    }
}
