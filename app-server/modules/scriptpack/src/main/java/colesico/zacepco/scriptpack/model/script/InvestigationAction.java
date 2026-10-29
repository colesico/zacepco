package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;

/**
 * Investigation action basics
 */
abstract public class InvestigationAction {

    /**
     * Cost of investigation action on subject
     */
    @YamlComment(text = {"Cost in resource units of investigation action on subject (int)"})
    public Integer cost;

    /**
     * Base points for investigation action
     */
    @YamlComment(text = "Base points for investigation action (int)")
    public Integer points;

    /**
     * Maximum number of elements in detective focus
     */
    @YamlComment(text = "Maximum number of elements in detective focus")
    public Integer focusLimit;

    /**
     * Cost of obtaining investigation subject statistics
     * (-1) - statistics is prohibited
     */
    @YamlComment(text = {
            "Cost of obtaining investigation subject statistics",
            "(-1) - statistics is prohibited",
            "(0) - free statistics",
            " Default - 0"
    })
    public Integer statCost = 0;

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
