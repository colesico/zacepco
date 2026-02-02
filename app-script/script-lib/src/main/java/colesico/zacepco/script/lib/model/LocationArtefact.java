package colesico.zacepco.script.lib.model;


/**
 * Location artifact for search
 */
public class LocationArtefact {

    /**
     * Search focus
     */
    public SignificantEntities focus;

    /**
     * Threshold decrement
     */
    public Integer delta;

    /**
     * Artifact discovery threshold
     */
    public Integer threshold;

    /**
     * Artifact
     */
    public Integer artifactId;

    /**
     * Bonus for artifact discovery
     */
    public Integer bonus;

    public SignificantEntities getFocus() {
        return focus;
    }

    public void setFocus(SignificantEntities focus) {
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

    public Integer getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Integer artifactId) {
        this.artifactId = artifactId;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }
}
