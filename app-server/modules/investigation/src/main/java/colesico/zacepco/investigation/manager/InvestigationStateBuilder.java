package colesico.zacepco.investigation.manager;

import colesico.zacepco.investigation.model.state.DetectiveState;
import colesico.zacepco.investigation.model.state.InvestigationState;
import colesico.zacepco.investigation.model.state.LocationState;
import colesico.zacepco.investigation.model.state.PersonageState;
import colesico.zacepco.script.model.scriptdoc.DetectiveSpec;
import colesico.zacepco.script.model.scriptdoc.ScriptDocument;

import java.util.*;
import java.util.stream.Collectors;

public class InvestigationStateBuilder {

    private final ScriptDocument script;

    // Задаем дефолтное значение, чтобы избежать NullPointerException
    private Integer detectivesNum = 4;
    private Double randFactor = 1.0;
    private Double budgetScale = 1.0;

    public InvestigationStateBuilder(ScriptDocument script) {
        this.script = script;
    }

    public InvestigationStateBuilder detectivesNum(Integer detectivesNum) {
        if (detectivesNum != null) {
            this.detectivesNum = detectivesNum;
        }
        return this;
    }

    public InvestigationStateBuilder randFactor(Double randFactor) {
        if (randFactor < 0 || randFactor > 1) {
            throw new IllegalArgumentException("Rand factor out of bounds [0-1]: " + randFactor);
        }
        this.randFactor = randFactor;
        return this;
    }

    public InvestigationStateBuilder budgetScale(Double budgetScale) {
        if (budgetScale < 0 || budgetScale > 3) {
            throw new IllegalArgumentException("Budget scale out of bounds [0-3]: " + budgetScale);
        }
        this.budgetScale = budgetScale;
        return this;
    }

    public ScriptDocument script() {
        return script;
    }

    public InvestigationState build() {
        var invState = new InvestigationState();

        invState.script.uid = script.metainfo.uid;
        invState.script.version = script.metainfo.version;

        invState.budget = (int) Math.ceil(script.investigation.budget * budgetScale);

        invState.scene.locations.putAll(buildLocationStates());
        invState.personages.putAll(buildPersonageStates());
        invState.detectives.putAll(buildDetectiveStates());

        if (invState.detectives.isEmpty()) {
            throw new RuntimeException("Detectives not specified");
        }

        return invState;
    }

    private Map<String, PersonageState> buildPersonageStates() {
        return script.crime.personages.values().stream()
                .collect(Collectors.toMap(
                        per -> per.id.toString(),
                        per -> {
                            var perState = new PersonageState(per.id);
                            perState.exposedClues.addAll(per.exposedClues);
                            perState.hidden = per.hidden;
                            return perState;
                        },
                        (u, v) -> u,
                        LinkedHashMap::new
                ));
    }

    private Map<String, LocationState> buildLocationStates() {
        return script.crime.scene.locations.values().stream()
                .collect(Collectors.toMap(
                        loc -> loc.id.toString(),
                        loc -> {
                            var locState = new LocationState(loc.id);
                            locState.exposedClues.addAll(loc.exposedClues);
                            locState.hidden = loc.hidden;
                            return locState;
                        },
                        (u, v) -> u,
                        LinkedHashMap::new
                ));
    }


    private Map<String, DetectiveState> buildDetectiveStates() {

        List<DetectiveSpec> allDetectives = new ArrayList<>(script.investigation.detectives.values());
        Collections.shuffle(allDetectives, new Random());

        return allDetectives.stream()
                .limit(Math.max(0, detectivesNum))
                .collect(Collectors.toMap(
                        det -> det.id.toString(),
                        det -> new DetectiveState(det.id),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }
}