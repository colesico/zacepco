package colesico.zacepco.script.lib.model;


import colesico.zacepco.script.lib.model.entity.EntityId;

import java.util.List;

/**
 * Location artifact to search
 */
public class SearchArtifact {

    /**
     * Entities on which search is focused
     */
    public List<EntityId> focus;

    /**
     * Threshold shift
     */
    public Integer shift;

    /**
     * Artifact discovery threshold
     */
    public Integer threshold;

    /**
     * Target artifacts
     */
    public List<EntityId> artifacts;

    /**
     * Bonus rate for artifact discovery
     */
    public Integer bonus;

    public List<EntityId> getFocus() {
        return focus;
    }

    public void setFocus(List<EntityId> focus) {
        this.focus = focus;
    }

    public Integer getShift() {
        return shift;
    }

    public void setShift(Integer shift) {
        this.shift = shift;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public List<EntityId> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<EntityId> artifacts) {
        this.artifacts = artifacts;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }
}
