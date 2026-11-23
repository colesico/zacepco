package colesico.zacepco.docket.ui.dto;

import colesico.zacepco.investigation.dto.DetectiveFocus;
import colesico.zacepco.investigation.dto.results.SearchResult;

public class LocationSearchResponse {
    public DetectiveFocus focus;
    public int d12;

    public final SearchResult result;

    public LocationSearchResponse(DetectiveFocus focus, int d12, SearchResult result) {
        this.focus = focus;
        this.d12 = d12;
        this.result = result;
    }
}
