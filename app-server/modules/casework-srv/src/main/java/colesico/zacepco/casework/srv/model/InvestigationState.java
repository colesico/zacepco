package colesico.zacepco.casework.srv.model;

import colesico.zacepco.respack.model.script.LocationId;
import colesico.zacepco.respack.model.script.PersonageId;

import java.util.HashMap;
import java.util.Map;

public class InvestigationState {


    public Integer budget;

    public Map<LocationId, LocationState> locations = new HashMap<>();
    public Map<PersonageId, PersonageState> personages;
}
