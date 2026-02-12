package colesico.zacepco.script.lib.model.entity;

import java.util.Objects;

/**
 * Entity identifier
 */
public final class EntityId {

    /**
     * Entity type
     */
    public final IdType type;

    /**
     * Id value
     */
    public final String value;

    public EntityId(IdType type, String value) {
        this.type = type;
        this.value = value;
    }

    public static EntityId of(String entityId){
        char type = entityId.charAt(0);
        String value = entityId.substring(1);
        return new EntityId(IdType.of(type),value);
    }

    public IdType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return type + value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EntityId entityId = (EntityId) o;
        return type == entityId.type && Objects.equals(value, entityId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }
}
