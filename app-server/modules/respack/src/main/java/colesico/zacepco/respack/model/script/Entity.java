package colesico.zacepco.respack.model.script;

import colesico.framework.assist.StringUtils;
import colesico.zacepco.respack.yaml.YamlComment;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Abstract entity model.
 * This class must be extended by specific entities.
 */
public abstract class Entity<ID extends Entity.TypedId> {

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

    /**
     * Entity identifier
     */
    abstract public sealed static class TypedId
            permits Clue.Id, Detective.Id, Location.Id, Personage.Id, TimeId {

        public static final Pattern VALUE_PATTERN = Pattern.compile("^[A-Z0-9]{1,3}$");

        /**
         * Id key
         */
        protected final String key;

        protected TypedId(String key) {
            if (key == null || !VALUE_PATTERN.matcher(key).matches()) {
                throw new IllegalArgumentException("Invalid Id key");
            }
            this.key = key;
        }

        abstract public Type type();

        public static TypedId parse(String entityId) {
            if (StringUtils.isBlank(entityId)) {
                return null;
            }
            Type entityType = Type.of(entityId.charAt(0));
            if (entityType == null) {
                throw new RuntimeException("Unknown entity type for id: " + entityId);
            }
            String value = entityId.substring(1);
            return switch (entityType) {
                case PERSONAGE -> new Personage.Id(value);
                case LOCATION -> new Location.Id(value);
                case CLUE -> new Clue.Id(value);
                case TIME -> new TimeId(value);
            };
        }

        public final String asString() {
            return type().code() + key;
        }

        public String key() {
            return key;
        }

        @Override
        public final String toString() {
            return type() + key;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Entity.TypedId entityId)) return false;
            return Objects.equals(key, entityId.key);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key);
        }
    }

    public enum Type {

        PERSONAGE('P'),
        LOCATION('L'),
        CLUE('C'),
        TIME('T');

        private final char code;

        Type(char code) {
            this.code = code;
        }

        public char code() {
            return code;
        }

        public static Type of(char code) {
            return switch (code) {
                case 'P' -> Type.PERSONAGE;
                case 'L' -> Type.LOCATION;
                case 'C' -> Type.CLUE;
                case 'T' -> Type.TIME;
                default -> null;
            };
        }

        @Override
        public String toString() {
            return "" + code;
        }
    }
}
