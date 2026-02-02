package colesico.zacepco.script.lib.model;

/**
 * Cost of evidence examination
 */
public class ExpertiseCost {

    /**
     * Cost for first attempt
     */
    public Integer initial;

    /**
     *  Cost for next attempts
     */
    public Integer retry;

    public Integer getInitial() {
        return initial;
    }

    public void setInitial(Integer initial) {
        this.initial = initial;
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }
}