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

    public Double randFactor;

    public Map<Location.Id, LocationState> locations = new HashMap<>();
    public Map<Personage.Id, PersonageState> personages = new HashMap<>();
    public Map<Detective.Id, DetectiveState> detectives = new HashMap<>();

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Double getRandFactor() {
        return randFactor;
    }

    public void setRandFactor(Double randFactor) {
        this.randFactor = randFactor;
    }

    public Map<Location.Id, LocationState> getLocations() {
        return locations;
    }

    public void setLocations(Map<Location.Id, LocationState> locations) {
        this.locations = locations;
    }

    public Map<Personage.Id, PersonageState> getPersonages() {
        return personages;
    }

    public void setPersonages(Map<Personage.Id, PersonageState> personages) {
        this.personages = personages;
    }

    public Map<Detective.Id, DetectiveState> getDetectives() {
        return detectives;
    }

    public void setDetectives(Map<Detective.Id, DetectiveState> detectives) {
        this.detectives = detectives;
    }
}
