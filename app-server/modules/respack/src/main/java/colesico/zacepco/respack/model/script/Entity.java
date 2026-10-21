package colesico.zacepco.respack.model.script;

import colesico.zacepco.respack.yaml.YamlComment;

import java.util.Objects;

/**
 * Abstract entity model.
 * This class must be extended by specific entities.
 */
public abstract class Entity<ID extends EntityId> {

    /**
     * Entity unique ID
     */
    @YamlComment(text = {
            "Unique within script ID",
            "Must match the corresponding asset ID from the script 'assets' field",
            "Format: [PLCT][A-Z0-9]{1,3}"
    })
    public ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
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
        return "Entity{" +
                "id=" + id +
                '}';
    }
}
