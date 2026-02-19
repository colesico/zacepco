package colesico.zacepco.script.lib.model.entity;

import java.util.List;

/**
 * Game character
 */
public class Personage extends Entity<PersonageId> {

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
    public List<LocationId> track;

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

    public List<LocationId> getTrack() {
        return track;
    }

    public void setTrack(List<LocationId> track) {
        this.track = track;
    }
}
