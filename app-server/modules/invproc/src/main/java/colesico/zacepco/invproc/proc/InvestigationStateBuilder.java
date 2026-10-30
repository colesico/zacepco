package colesico.zacepco.invproc.proc;

import colesico.zacepco.invproc.model.DetectiveState;
import colesico.zacepco.invproc.model.InvestigationState;
import colesico.zacepco.invproc.model.LocationState;
import colesico.zacepco.scriptpack.model.script.DetectiveSpec;
import colesico.zacepco.scriptpack.model.script.LocationSpec;
import colesico.zacepco.scriptpack.model.script.ScriptDocument;

import java.util.*;
import java.util.stream.Collectors;

public class InvestigationStateBuilder {

    private final ScriptDocument script;

    /**
     * Detectives number for investigation
     */
    private Integer detectivesNum;

    /**
     * Rand factor or null
     * Value [0.0 ... 1.0]
     */
    private Double randFactor;

    public InvestigationStateBuilder(ScriptDocument script) {
        this.script = script;
    }

    public InvestigationStateBuilder detectivesNum(Integer detectivesNum) {
        this.detectivesNum = detectivesNum;
        return this;
    }

    public InvestigationStateBuilder randFactor(Double randFactor) {
        this.randFactor = randFactor;
        return this;
    }

    public InvestigationState build() {
        var invState = new InvestigationState();

        invState.setBudget(script.investigation.budget);
        invState.setRandFactor(script.investigation.randFactor(randFactor));

        invState.detectives.putAll(buildDetectiveStates());
        invState.locations.putAll(buildLocationStates());

        return invState;
    }

    private Map<LocationSpec.Id, LocationState> buildLocationStates() {
        Map<LocationSpec.Id, LocationState> locationStates = script.crime.scene.locations.stream()
                .collect(Collectors.toMap(
                        LocationSpec::getId,
                        loc -> {
                            var locState = new LocationState(loc.id);
                            locState.exposedClues.addAll(loc.exposedClues);
                            locState.hidden = loc.hidden();
                            return locState;
                        }
                ));
        return locationStates;
    }

    private Map<DetectiveSpec.Id, DetectiveState> buildDetectiveStates() {
        List<DetectiveSpec> allDetectives = new ArrayList<>(script.investigation.detectives);
        Collections.shuffle(allDetectives, new Random());
        var randomDetectives = allDetectives.subList(0, Math.min(detectivesNum, allDetectives.size()));

        Map<DetectiveSpec.Id, DetectiveState> resultDetectives = randomDetectives.stream()
                .collect(Collectors.toMap(
                        DetectiveSpec::getId,
                        det -> new DetectiveState(det.id)        // Значение: сама сущность
                ));

        return resultDetectives;
    }
}
