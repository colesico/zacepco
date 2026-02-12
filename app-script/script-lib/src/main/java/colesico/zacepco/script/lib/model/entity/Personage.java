package colesico.zacepco.script.lib.model.entity;

import java.util.List;

/**
 * Game character
 */
public class Personage extends Entity {

    /**
     * Brief personage dossier
     */
    public String dossier;

    /**
     * Hidden personage or not
     */
    public Boolean hidden;

    /**
     * Track of personage movement across locations for each  time tick
     */
    public List<EntityId> track;

    public String getDossier() {
        return dossier;
    }

    public void setDossier(String dossier) {
        this.dossier = dossier;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

}
