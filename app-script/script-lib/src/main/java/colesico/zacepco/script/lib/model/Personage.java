package colesico.zacepco.script.lib.model;

/**
 * Game character
 */
public class Personage {

    public Integer id;

    /**
     * Personage name
     */
    public String name;

    /**
     * Brief personage dossier
     */
    public String dossier;

    /**
     * Hidden personage or not
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
