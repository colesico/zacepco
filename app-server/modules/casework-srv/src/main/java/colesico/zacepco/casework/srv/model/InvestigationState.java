package colesico.zacepco.casework.srv.model;

import colesico.zacepco.respack.model.script.Location;
import colesico.zacepco.respack.model.script.Personage;

import java.util.HashMap;
import java.util.Map;

public class InvestigationState {


    public Integer budget;

    public Map<Location.Id, LocationState> locations = new HashMap<>();
    public Map<Personage.Id, PersonageState> personages;
}
