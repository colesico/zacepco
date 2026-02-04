package colesico.zacepco.script.lib.model;

import java.util.List;

/**
 * Personages dictionary
 */
public class PersonageDict {

    public List<Personage> suspects;

    public List<Personage> getSuspects() {
        return suspects;
    }

    public void setSuspects(List<Personage> suspects) {
        this.suspects = suspects;
    }
}
