package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlIgnore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Abstract entity model.
 * This class must be extended by specific entities.
 */
public abstract class EntitySpec<ID extends TId> implements ScriptReferencer {

    @YamlIgnore
    public ID id;

    /**
     * Entity short name
     */
    @YamlComment(text = "Short name")
    public String name = "";

    /**
     * Detailed description.
     */
    @YamlComment(text = {
            "Detailed description",
    })
    public String description = "";

    @YamlComment(text = {
            "Reference script ID",
            "If this entity is imported from another script, specify the ID of that reference script."
    })
    public String ref;

    @YamlComment(text = {
            "Placeholder values",
            "Map of placeholder names to values substituted into the entity's description or name"
    })
    public Map<String, String> placeholders = new HashMap<>();

    @Override
    public String ref() {
        return ref;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof EntitySpec<?> that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }

}
