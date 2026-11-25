package colesico.zacepco.investigation.model.state;

import colesico.zacepco.script.model.scriptdoc.ClueSpec;
import colesico.zacepco.script.model.scriptdoc.DetectiveSpec;
import colesico.zacepco.script.model.scriptdoc.PersonageSpec;
import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.*;

public class InvestigationState {

    /**
     * Script reference
     */
    public ScriptRef script = new ScriptRef();

    /**
     * Current investigation budget
     */
    public int budget = 0;

    public double randFactor = 1.0;

    /**
     * Current detective
     */
    public DetectiveSpec.Id activeDetectiveId;

    public final Map<PersonageSpec.Id, PersonageState> personages = new LinkedHashMap<>();
    public SceneState scene = new SceneState();
    public final Map<ClueSpec.Id, ClueState> clues = new LinkedHashMap<>();
    public final Map<DetectiveSpec.Id, DetectiveState> detectives = new LinkedHashMap<>();


    public InvestigationState() {
    }

    /**
     * Set next active detective
     *
     * @return active detective id
     */
    public DetectiveSpec.Id nextDetective() {
        if (detectives.isEmpty()) {
            throw new RuntimeException("Detectives not defined");
        }

        if (activeDetectiveId == null) {
            activeDetectiveId = detectives.keySet().iterator().next();
            return activeDetectiveId;
        }

        var iterator = detectives.keySet().iterator();

        while (iterator.hasNext()) {
            if (iterator.next().equals(activeDetectiveId)) {
                if (iterator.hasNext()) {
                    activeDetectiveId = iterator.next();
                } else {
                    activeDetectiveId = detectives.keySet().iterator().next();
                }
                return activeDetectiveId;
            }
        }

        activeDetectiveId = detectives.keySet().iterator().next();
        return activeDetectiveId;
    }

    public DetectiveState activeDetective() {
        if (activeDetectiveId == null) {
            nextDetective();
        }
        return detectives.get(activeDetectiveId);
    }

    public boolean spendBudget(int value) {
        if (budget > 0) {
            this.budget = this.budget - value;
            return true;
        }
        return false;
    }

    /**
     * All discovered entities
     */
    public Set<TId> findDiscoveredEntities() {
        Set<TId> discovered = new LinkedHashSet<>();

        for (var personage : personages.values()) {
            if (!personage.hidden) {
                discovered.add(personage.id);
            }
        }

        for (var location : scene.locations.values()) {
            if (!location.hidden) {
                discovered.add(location.id);
            }
        }

        for (var clue : clues.values()) {
            if (clue.discovered) {
                discovered.add(clue.id);
            }
        }

        return discovered;
    }
}
