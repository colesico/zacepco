package colesico.zacepco.investigation.manager;

import colesico.zacepco.investigation.model.state.*;
import colesico.zacepco.script.model.scriptdoc.*;

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
        invState.randFactor = randFactor;

        invState.personages.putAll(buildPersonageStates());
        invState.scene.locations.putAll(buildLocationStates());
        invState.scene.ways.putAll(buildWayStates());
        invState.clues.putAll(buildClueStates());
        invState.detectives.putAll(buildDetectiveStates());

        if (invState.detectives.isEmpty()) {
            throw new RuntimeException("Detectives not specified");
        }

        invState.activeDetectiveId = invState.detectives.values().iterator().next().id;

        return invState;
    }

    private Map<PersonageSpec.Id, PersonageState> buildPersonageStates() {
        return script.crime.personages.values().stream()
                .collect(Collectors.toMap(
                        per -> per.id,
                        per -> {
                            var perState = new PersonageState(per.id);
                            perState.hidden = per.hidden;
                            return perState;
                        },
                        (u, v) -> u,
                        LinkedHashMap::new
                ));
    }

    private Map<LocationSpec.Id, LocationState> buildLocationStates() {
        return script.crime.scene.locations.values().stream()
                .collect(Collectors.toMap(
                        loc -> loc.id,
                        loc -> {
                            var locState = new LocationState(loc.id);
                            locState.hidden = loc.hidden;
                            return locState;
                        },
                        (u, v) -> u,
                        LinkedHashMap::new
                ));
    }

    private Map<WaySpec.Id, WayState> buildWayStates() {
        return script.crime.scene.ways.values().stream()
                .collect(Collectors.toMap(
                        way -> way.id,
                        way -> {
                            var locState = new WayState(way.id);
                            locState.hidden = way.hidden;
                            return locState;
                        },
                        (u, v) -> u,
                        LinkedHashMap::new
                ));
    }

    private Map<ClueSpec.Id, ClueState> buildClueStates() {
        return script.crime.clues.values().stream()
                .collect(Collectors.toMap(
                        clu -> clu.id,
                        clu -> {
                            var cluState = new ClueState(clu.id);
                            cluState.discovered = clu.discovered;
                            return cluState;
                        },
                        (u, v) -> u,
                        LinkedHashMap::new
                ));
    }

    private Map<DetectiveSpec.Id, DetectiveState> buildDetectiveStates() {

        List<DetectiveSpec> allDetectives = new ArrayList<>(script.investigation.detectives.values());
        Collections.shuffle(allDetectives, new Random());

        return allDetectives.stream()
                .limit(Math.max(0, detectivesNum))
                .collect(Collectors.toMap(
                        det -> det.id,
                        det -> new DetectiveState(det.id),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }
}