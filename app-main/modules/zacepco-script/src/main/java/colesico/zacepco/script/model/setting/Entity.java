package colesico.zacepco.script.model.setting;

import colesico.zacepco.script.yaml.YamlComment;

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
    @YamlComment(text = "Unique ID")
    public ID id;

    /**
     * Short name
     */
    public String name;

    /**
     * Detailed description.
     * Entity identifiers and placeholders can be specified with a '$' prefix.
     * Examples: Location is $L2. Personage say $MSG
     */
    @YamlComment(text = {
            "Detailed description.",
            "Entity identifiers and placeholders can be specified with a '$' prefix.",
            "Examples: Location is $L2. Personage say $MSG"
    })
    public String description;

    @YamlComment(text = "Entity reference")
    public EntityReference reference;

    /**
     * Placeholder values
     */
    @YamlComment(text = {
            "Variables definitions",
            "Variable can be used in entity texts referenced by '$' prefix",
            "Examples: CALIBRE: .37. Then in clue description: Gun calibre $CALIBRE"
    })
    public Map<String, String> vars;

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

    public Map<String, String> getVars() {
        return vars;
    }

    public void setVars(Map<String, String> vars) {
        this.vars = vars;
    }

    public EntityReference getReference() {
        return reference;
    }

    public void setReference(EntityReference reference) {
        this.reference = reference;
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
