package colesico.zacepco.investigation.model.state;

import java.util.LinkedHashMap;
import java.util.Map;

public class InvestigationState {

    /**
     * Script reference
     */
    public ScriptRef script = new ScriptRef();

    /**
     * Current investigation budget
     */
    public int budget = 0;

    /**
     * Current detective
     */
    public String activeDetectiveId;

    public SceneState scene = new SceneState();
    public final Map<String, PersonageState> personages = new LinkedHashMap<>();
    public final Map<String, DetectiveState> detectives = new LinkedHashMap<>();


    public InvestigationState() {
    }

    /**
     * Set next active detective
     * @return active detective id
     */
    public String nextDetective() {
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

}
