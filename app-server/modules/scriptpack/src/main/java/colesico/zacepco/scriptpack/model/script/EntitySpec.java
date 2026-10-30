package colesico.zacepco.scriptpack.model.script;

import colesico.framework.assist.StringUtils;
import colesico.zacepco.scriptpack.yaml.YamlComment;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Abstract entity model.
 * This class must be extended by specific entities.
 */
public abstract class EntitySpec<ID extends EntitySpec.UID> {

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
    public String name = "Name";

    /**
     * Detailed description.
     */
    @YamlComment(text = {
            "Detailed description",
    })
    public String description = "Description";

    @YamlComment(text = {
            "Optional asset ID",
            "Used when appropriate asset id is not match the entity id",
    })
    public ID asset;

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

    public ID asset() {
        return asset != null ? asset : id;
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

    /**
     * Entity identifier
     */
    abstract public sealed static class UID
            permits ClueSpec.Id, DetectiveSpec.Id, LocationSpec.Id, PersonageSpec.Id, TickSpec.Id {

        public static final Pattern KEY_PATTERN = Pattern.compile("^([A-Z0-9]{1,3})$");

        /**
         * Id key
         */
        public final String key;

        protected UID(String key) {
            if (key == null || !KEY_PATTERN.matcher(key).matches()) {
                throw new IllegalArgumentException("Invalid UID key");
            }
            this.key = key;
        }

        abstract public Type type();

        public static UID parse(String uid) {
            if (StringUtils.isBlank(uid)) {
                return null;
            }
            Type type = Type.of(uid.charAt(0));
            if (type == null) {
                throw new RuntimeException("Unknown type for UID: " + uid);
            }
            String key = uid.substring(1);
            return switch (type) {
                case PERSONAGE -> new PersonageSpec.Id(key);
                case LOCATION -> new LocationSpec.Id(key);
                case CLUE -> new ClueSpec.Id(key);
                case TIME -> new TickSpec.Id(key);
                case DETECTIVE -> new DetectiveSpec.Id(key);
            };
        }

        @Override
        public final String toString() {
            return type().code + key;
        }

        @Override
        public final boolean equals(Object o) {
            if (!(o instanceof EntitySpec.UID uid)) return false;
            return Objects.equals(toString(), uid.toString());
        }

        @Override
        public final int hashCode() {
            return Objects.hashCode(toString());
        }
    }

    public enum Type {

        PERSONAGE('P'),
        LOCATION('L'),
        CLUE('C'),
        TIME('T'),
        DETECTIVE('D');

        public final char code;

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
        public final String toString() {
            return "" + code;
        }

    }
}
