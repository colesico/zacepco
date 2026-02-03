package colesico.zacepco.script.lib.model;

/**
 * Miscellaneous  definition
 */
public class Miscellaneous {

    /**
     * Crime duration in ticks
     */
    public Integer duration;

    /**
     * Investigation budget (resource)
     */
    public Integer budget;

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }
}
