package colesico.zacepco.investigation.model.state;

import colesico.zacepco.script.model.scriptdoc.DetectiveSpec;

public class DetectiveState {

    /**
     * Detective id
     */
    public DetectiveSpec.Id id;

    /**
     * Awarded points
     */
    public int points = 0;

    public DetectiveState() {
    }

    public DetectiveState(DetectiveSpec.Id id) {
        this.id = id;
    }

    public int addPoints(int value) {
        points = points + value;
        return points;
    }

}
