package colesico.zacepco.script.lib.model.search;

import java.util.List;

/**
 * Search specification
 */
public class Search {
    /**
     * Cost of searching any location
     */
    public Integer cost;

    /**
     * Base points for searching
     */
    public Integer points;

    /**
     * Maximum number of elements in detective focus
     */
    public Integer focusLimit;

    /**
     * Cost in resources of obtaining location statistics before a search.
     * (-1) - statistics is prohibited
     */
    public Integer statCost;

    public List<SearchLocation> locations;

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getFocusLimit() {
        return focusLimit;
    }

    public void setFocusLimit(Integer focusLimit) {
        this.focusLimit = focusLimit;
    }

    public Integer getStatCost() {
        return statCost;
    }

    public void setStatCost(Integer statCost) {
        this.statCost = statCost;
    }

    public List<SearchLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<SearchLocation> locations) {
        this.locations = locations;
    }
}
