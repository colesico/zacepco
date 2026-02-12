package colesico.zacepco.script.lib.model.entity;

import java.util.Objects;

/**
 * Abstract entity model.
 * This class must be extended by specific entities.
 */
public abstract class Entity {

    /**
     * Entity Id
     */
    public EntityId id;

    /**
     * Short name
     */
    public String name;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
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
