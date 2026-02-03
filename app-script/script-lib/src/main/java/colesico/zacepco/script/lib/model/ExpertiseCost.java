package colesico.zacepco.script.lib.model;

public class ExpertiseCost {

    /**
     * Initial expertise cost  (resources amount)
     */
    public Integer initial;

    /**
     * Subsequent expertises cost
     */
    public Integer subs;

    public Integer getInitial() {
        return initial;
    }

    public void setInitial(Integer initial) {
        this.initial = initial;
    }

    public Integer getSubs() {
        return subs;
    }

    public void setSubs(Integer subs) {
        this.subs = subs;
    }
}
