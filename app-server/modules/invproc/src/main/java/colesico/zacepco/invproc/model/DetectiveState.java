package colesico.zacepco.invproc.model;

public class DetectiveState {

    public final String id;

    /**
     * Awarded points
     */
    public int points = 0;

    public DetectiveState(String id) {
        this.id = id;
    }

    public int addPoints(int value) {
        points = points + value;
        return points;
    }

}
