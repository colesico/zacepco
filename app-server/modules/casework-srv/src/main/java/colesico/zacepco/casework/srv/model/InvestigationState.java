package colesico.zacepco.casework.srv.model;

import colesico.zacepco.scriptpack.model.script.DetectiveSpec;
import colesico.zacepco.scriptpack.model.script.LocationSpec;
import colesico.zacepco.scriptpack.model.script.PersonageSpec;

import java.util.HashMap;
import java.util.Map;

public class InvestigationState {

    /**
     * Current investigation budget
     */
    public Integer budget;

    public Double randFactor;

    public Map<LocationSpec.Id, LocationState> locations = new HashMap<>();
    public Map<PersonageSpec.Id, PersonageState> personages = new HashMap<>();
    public Map<DetectiveSpec.Id, DetectiveState> detectives = new HashMap<>();

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

    public Map<LocationSpec.Id, LocationState> getLocations() {
        return locations;
    }

    public void setLocations(Map<LocationSpec.Id, LocationState> locations) {
        this.locations = locations;
    }

    public Map<PersonageSpec.Id, PersonageState> getPersonages() {
        return personages;
    }

    public void setPersonages(Map<PersonageSpec.Id, PersonageState> personages) {
        this.personages = personages;
    }

    public Map<DetectiveSpec.Id, DetectiveState> getDetectives() {
        return detectives;
    }

    public void setDetectives(Map<DetectiveSpec.Id, DetectiveState> detectives) {
        this.detectives = detectives;
    }
}
