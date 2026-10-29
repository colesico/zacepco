package colesico.zacepco.casework.srv.model;

import colesico.zacepco.scriptpack.model.script.Detective;

public class DetectiveState {

    public Detective.Id id;

    /**
     * Awarded points
     */
    public Integer points = 0;

    public DetectiveState(Detective.Id id) {
        this.id = id;
    }

    public DetectiveState() {
    }

    public Detective.Id getId() {
        return id;
    }

    public void setId(Detective.Id id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
