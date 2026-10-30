package colesico.zacepco.invproc.model;

import colesico.zacepco.scriptpack.model.script.DetectiveSpec;

public class DetectiveState {

    public DetectiveSpec.Id id;

    /**
     * Awarded points
     */
    public Integer points = 0;

    public DetectiveState(DetectiveSpec.Id id) {
        this.id = id;
    }

    public DetectiveState() {
    }

    public DetectiveSpec.Id getId() {
        return id;
    }

    public void setId(DetectiveSpec.Id id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
