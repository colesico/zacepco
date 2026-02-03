package colesico.zacepco.script.lib.model;

public class SearchCost {

    /**
     * Base search cost
     */
    public Integer base;

    /**
     * Extra cost for thoroughness
     */
    public Integer detailed;

    public Integer getBase() {
        return base;
    }

    public void setBase(Integer base) {
        this.base = base;
    }

    public Integer getDetailed() {
        return detailed;
    }

    public void setDetailed(Integer detailed) {
        this.detailed = detailed;
    }
}
