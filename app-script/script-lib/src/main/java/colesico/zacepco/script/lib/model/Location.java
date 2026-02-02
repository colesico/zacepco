package colesico.zacepco.script.lib.model;

import java.util.List;

/**
 * Game field location
 */
public class Location {

    public Integer id;

    public String name;

    /**
     * General passages.
     * Location IDs where the passages lead from this location
     */
    public List<Integer> passages;

    /**
     * Hidden passages.
     * Location IDs where the passages lead from this location
     */
    public List<Integer> hiddenPassages;

    /**
     * Hidden location or not
     */
    public Boolean hidden;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPassages() {
        return passages;
    }

    public void setPassages(List<Integer> passages) {
        this.passages = passages;
    }

    public List<Integer> getHiddenPassages() {
        return hiddenPassages;
    }

    public void setHiddenPassages(List<Integer> hiddenPassages) {
        this.hiddenPassages = hiddenPassages;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }
}
