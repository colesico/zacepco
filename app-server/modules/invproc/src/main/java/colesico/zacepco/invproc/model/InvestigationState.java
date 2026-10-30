package colesico.zacepco.invproc.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class InvestigationState {

    public final String scriptId;

    /**
     * Current investigation budget
     */
    public int currentBudget = 0;

    public double randFactor = 1.0;

    /**
     * Current detective
     */
    public String activeDetectiveId;

    public final Map<String, LocationState> locations = new LinkedHashMap<>();
    public final Map<String, PersonageState> personages = new LinkedHashMap<>();
    public final Map<String, DetectiveState> detectives = new LinkedHashMap<>();

    public InvestigationState(String scriptId) {
        this.scriptId = scriptId;
    }

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
