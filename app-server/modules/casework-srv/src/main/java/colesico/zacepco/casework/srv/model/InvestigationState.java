package colesico.zacepco.casework.srv.model;

import colesico.zacepco.scriptpack.model.script.Detective;
import colesico.zacepco.scriptpack.model.script.Location;
import colesico.zacepco.scriptpack.model.script.Personage;

import java.util.HashMap;
import java.util.Map;

public class InvestigationState {

    /**
     * Current investigation budget
     */
    public Integer budget;

    public Map<Location.Id, LocationState> locations = new HashMap<>();
    public Map<Personage.Id, PersonageState> personages;
    public Map<Detective.Id, DetectiveState> detectives;
}
