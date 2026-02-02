package colesico.zacepco.script.lib.model;


/**
 * Location artifact to search
 */
public class SearchArtifact {

    /**
     * Search focus
     */
    public Significative focus;

    /**
     * Threshold shift
     */
    public Integer shift;

    /**
     * Artifact discovery threshold
     */
    public Integer threshold;

    /**
     * Artifact
     */
    public Integer artifact;

    /**
     * Bonus for artifact discovery
     */
    public Integer bonus;

    public Significative getFocus() {
        return focus;
    }

    public void setFocus(Significative focus) {
        this.focus = focus;
    }

    public Integer getDelta() {
        return delta;
    }

    public void setDelta(Integer delta) {
        this.delta = delta;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Integer getArtifact() {
        return artifact;
    }

    public void setArtifact(Integer artifact) {
        this.artifact = artifact;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }
}
