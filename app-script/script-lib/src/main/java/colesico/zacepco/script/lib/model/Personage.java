package colesico.zacepco.script.lib.model;

import java.util.Objects;

/**
 * Game character
 */
public class Personage {

    public EntityId id;

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

    public EntityId getId() {
        return id;
    }

    public void setId(EntityId id) {
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

    @Override
    public String toString() {
        return "Personage{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", hidden=" + hidden +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Personage personage = (Personage) o;
        return Objects.equals(id, personage.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
