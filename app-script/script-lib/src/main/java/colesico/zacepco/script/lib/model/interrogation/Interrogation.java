package colesico.zacepco.script.lib.model.interrogation;

import java.util.List;

/**
 * Interrogation specification
 */
public class Interrogation {

    /**
     * Cost of interrogation any personage
     */
    public Integer cost;

    /**
     * Base points for interrogation
     */
    public Integer points;

    /**
     * Maximum number of elements in detective focus
     */
    public Integer focusLimit;

    /**
     * Cost in resources of obtaining personage statistics before an interrogation.
     * (-1) - statistics is prohibited
     */
    public Integer statCost;

    public List<InterrogationPersonage> personages;

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

    public List<InterrogationPersonage> getPersonages() {
        return personages;
    }

    public void setPersonages(List<InterrogationPersonage> personages) {
        this.personages = personages;
    }
}
