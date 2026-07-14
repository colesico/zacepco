package colesico.zacepco.script.model.script;

import colesico.zacepco.script.yaml.YamlComment;

/**
 * Miscellaneous  definition
 */
public class Miscellaneous {

    /**
     * Crime duration in ticks
     */
    @YamlComment("Crime duration in time ticks")
    public Integer duration;

    /**
     * Investigation budget (resource)
     */
    @YamlComment("Investigation budget")
    public Integer budget;

    /**
     * Randomness factor [1..0]. Used to recalculate table thresholds to change the randomness factor.
     * New_Threshold = Table_Threshold/(12 * randomnessFactor)
     * Default = 1
     */
    @YamlComment("Randomness factor [1..0]. Used to recalculate table thresholds to adjust randomness. New_threshold = Table_threshold/(12 * randomnessFactor) Default = 1")
    public Double randFactor;

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
