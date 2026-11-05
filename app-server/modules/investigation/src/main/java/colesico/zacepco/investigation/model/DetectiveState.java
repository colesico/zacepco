package colesico.zacepco.investigation.model;

public class DetectiveState {

    public String id;

    /**
     * Awarded points
     */
    public int points = 0;

    public DetectiveState() {
    }

    public DetectiveState(String id) {
        this.id = id;
    }

    public int addPoints(int value) {
        points = points + value;
        return points;
    }

}
