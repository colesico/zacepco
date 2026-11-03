package colesico.zacepco.investigation.model;

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

        invState.scriptId = script.metainfo.id;
        invState.scriptVersion = script.metainfo.version;
        invState.currentBudget = (int) Math.ceil(script.investigation.budget * budgetScale);
        invState.randFactor = randFactor;

        invState.detectives.putAll(buildDetectiveStates());
        invState.locations.putAll(buildLocationStates());
        invState.personages.putAll(buildPersonageStates());

        if (!invState.detectives.isEmpty()) {
            throw new RuntimeException("Detectives not specified");
        }

        return invState;
    }

    private Map<String, PersonageState> buildPersonageStates() {
        return script.crime.personages.stream()
                .collect(Collectors.toMap(
                        per -> per.id.toString(),
                        per -> {
                            var perState = new PersonageState(per.id.toString());
                            perState.exposedClues.addAll(per.exposedClues);
                            perState.hidden = per.hidden;
                            return perState;
                        },
                        (u, v) -> u,
                        LinkedHashMap::new
                ));
    }

    private Map<String, LocationState> buildLocationStates() {
        return script.crime.scene.locations.stream()
                .collect(Collectors.toMap(
                        loc -> loc.id.toString(),
                        loc -> {
                            var locState = new LocationState(loc.id.toString());
                            locState.exposedClues.addAll(loc.exposedClues);
                            locState.hidden = loc.hidden;
                            return locState;
                        },
                        (u, v) -> u,
                        LinkedHashMap::new
                ));
    }


    private Map<String, DetectiveState> buildDetectiveStates() {
        if (script.investigation.detectives == null || script.investigation.detectives.isEmpty()) {
            return new LinkedHashMap<>();
        }

        List<DetectiveSpec> allDetectives = new ArrayList<>(script.investigation.detectives);
        Collections.shuffle(allDetectives, new Random());

        // Ограничиваем стрим вместо опасного subList и собираем строго в LinkedHashMap
        return allDetectives.stream()
                .limit(Math.max(0, detectivesNum))
                .collect(Collectors.toMap(
                        det -> det.id.toString(),
                        det -> new DetectiveState(det.id.toString()),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }
}