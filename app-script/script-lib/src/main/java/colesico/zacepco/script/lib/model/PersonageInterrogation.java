package colesico.zacepco.script.lib.model;

import java.util.List;

/**
 * Personage interrogation
 */
public class PersonageInterrogation {

    /**
     * Personage Id
     */
    public Integer presonage;

    /**
     * Interrogation  cost  (resource)
     */
    public Integer cost;

    public List<PersonageArtifact> artifacts;

    public Integer getPresonage() {
        return presonage;
    }

    public void setPresonage(Integer presonage) {
        this.presonage = presonage;
    }
}
