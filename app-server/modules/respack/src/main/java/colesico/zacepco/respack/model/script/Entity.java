package colesico.zacepco.respack.model.script;

import colesico.framework.assist.StringUtils;
import colesico.zacepco.respack.yaml.YamlComment;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Abstract entity model.
 * This class must be extended by specific entities.
 */
public abstract class Entity<ID extends Entity.UID> {

    /**
     * Entity unique ID
     */
    @YamlComment(text = {
            "Unique within script ID",
            "Must match the corresponding asset ID",
            "Format: [PLCTD][A-Z0-9]{1,3}"
    })
    public ID id;

    /**
     * Entity short name
     */
    @YamlComment(text = "Short name")
    public String name;

    /**
     * Detailed description.
     */
    @YamlComment(text = {
            "Detailed description",
    })
    public String description;

    @YamlComment(text = {
            "Optional asset ID",
            "Used when appropriate asset id is not match the entity id",
    })
    public ID asset;

    public ID asset() {
        return asset != null ? asset : id;
    }

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

    public ID getAsset() {
        return asset;
    }

    public void setAsset(ID asset) {
        this.asset = asset;
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
    abstract public sealed static class UID
            permits Clue.Id, Detective.Id, Location.Id, Personage.Id, TimeId {

        public static final Pattern VALUE_PATTERN = Pattern.compile("^[A-Z0-9]{1,3}$");

        /**
         * Id key
         */
        protected final String key;

        protected UID(String key) {
            if (key == null || !VALUE_PATTERN.matcher(key).matches()) {
                throw new IllegalArgumentException("Invalid Id key");
            }
            this.key = key;
        }

        abstract public Type type();

        public static UID parse(String entityId) {
            if (StringUtils.isBlank(entityId)) {
                return null;
            }
            Type entityType = Type.of(entityId.charAt(0));
            if (entityType == null) {
                throw new RuntimeException("Unknown entity type for id: " + entityId);
            }
            String key = entityId.substring(1);
            return switch (entityType) {
                case PERSONAGE -> new Personage.Id(key);
                case LOCATION -> new Location.Id(key);
                case CLUE -> new Clue.Id(key);
                case TIME -> new TimeId(key);
                case DETECTIVE -> new Detective.Id(key);
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
            if (!(o instanceof Entity.UID entityId)) return false;
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
        TIME('T'),
        DETECTIVE('D');

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
                case 'D' -> Type.DETECTIVE;
                default -> null;
            };
        }

        @Override
        public String toString() {
            return "" + code;
        }
    }
}
