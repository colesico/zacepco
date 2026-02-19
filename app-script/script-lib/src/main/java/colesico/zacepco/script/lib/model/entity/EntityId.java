package colesico.zacepco.script.lib.model.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * Entity identifier
 */
abstract public sealed class EntityId permits PersonageId, ClueId, LocationId, TimeId {

    /**
     * Entity type
     */
    public final IdType type;

    /**
     * Id value
     */
    public final String value;

    protected EntityId(IdType type, String value) {
        if (StringUtils.isBlank(value)) {
            throw new RuntimeException("Empty id value");
        }
        if (type == null) {
            throw new RuntimeException("Id type is null");
        }
        this.type = type;
        this.value = value;
    }

    public static EntityId parse(String entityId) {
        if (StringUtils.isBlank(entityId)) {
            return null;
        }
        IdType type = IdType.of(entityId.charAt(0));
        if (type == null) {
            throw new RuntimeException("Unknown entity type for id: " + entityId);
        }
        String value = entityId.substring(1);
        return switch (type) {
            case PERSONAGE -> new PersonageId(value);
            case LOCATION -> new LocationId(value);
            case CLUE -> new ClueId(value);
            case TIME -> new TimeId(value);
        };
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
