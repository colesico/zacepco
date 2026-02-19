package colesico.zacepco.script.lib.model.inestigation;

/**
     * Investigation action basics
 */
abstract public class InvestigationAction {

    /**
     * Cost of investigation action on subject
     */
    public Integer cost;

    /**
     * Base points for investigation action
     */
    public Integer points;

    /**
     * Maximum number of elements in detective focus
     */
    public Integer focusLimit;

    /**
     * Cost of obtaining investigation subject statistics
     * (-1) - statistics is prohibited
     */
    public Integer statCost;

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
}
