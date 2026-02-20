package colesico.zacepco.script.model.entity;

import java.util.Objects;

/**
 * Abstract entity model.
 * This class must be extended by specific entities.
 */
public abstract class Entity<ID extends EntityId> {

    /**
     * Entity unique ID
     */
    public ID id;

    /**
     * Short name
     */
    public String name;

    /**
     * Detailed description.
     * Entity identifiers can be specified in the description for references.
     * The identifier is specified starting with a '$' prefix.
     */
    public String description;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?> entity = (Entity<?>) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return getClass().getCanonicalName() + "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
