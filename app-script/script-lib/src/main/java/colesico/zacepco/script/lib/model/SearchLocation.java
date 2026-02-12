package colesico.zacepco.script.lib.model;

import colesico.zacepco.script.lib.model.entity.EntityId;

import java.util.List;

/**
 * Location search
 */
public class SearchLocation {

    /**
     * Location to be search
     */
    public EntityId location;

    public SearchCost cost;

    /**
     * Artifact definitions
     */
    public List<SearchArtifact> artifacts;

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public SearchCost getCost() {
        return cost;
    }

    public void setCost(SearchCost cost) {
        this.cost = cost;
    }

    public List<SearchArtifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<SearchArtifact> artifacts) {
        this.artifacts = artifacts;
    }
}
