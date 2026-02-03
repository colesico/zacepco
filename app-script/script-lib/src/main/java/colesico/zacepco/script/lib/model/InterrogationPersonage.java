package colesico.zacepco.script.lib.model;

import java.util.List;

/**
 * Personage interrogation
 */
public class InterrogationPersonage {

    /**
     * Personage Id
     */
    public Integer presonage;

    /**
     * Interrogation cost  (resource)
     */
    public Integer cost;

    public List<InterrogationArtifact> artifacts;

    public Integer getPresonage() {
        return presonage;
    }

    public void setPresonage(Integer presonage) {
        this.presonage = presonage;
    }
}
