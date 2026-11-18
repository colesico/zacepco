package colesico.zacepco.docket.ui.dto;

import colesico.zacepco.investigation.dto.action.LocationSearchResult;

public class LocationSearchResponse {
    public final String code;
    public final LocationSearchResult details;

    public LocationSearchResponse(LocationSearchResult details) {
        this.code = details.code();
        this.details = details;
    }
}
