package colesico.zacepco.scriptpack.model.script;

/**
 * Integer interval
 */
public final class IntInterval {

    /**
     * Interval min value
     */
    public Integer min;

    /**
     * Max value
     */
    public Integer max;

    public IntInterval() {
    }

    public IntInterval(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }
}
