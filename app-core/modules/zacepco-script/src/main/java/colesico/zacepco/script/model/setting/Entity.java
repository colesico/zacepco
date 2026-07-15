package colesico.zacepco.script.model.setting;

import java.util.Map;
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

    /**
     * Placeholder values for target basic entity
     */
    public Map<String,String> placeholders;

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

    public Map<String, String> getPlaceholders() {
        return placeholders;
    }

    public void setPlaceholders(Map<String, String> placeholders) {
        this.placeholders = placeholders;
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
