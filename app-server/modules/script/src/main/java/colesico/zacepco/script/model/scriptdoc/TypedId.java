package colesico.zacepco.script.model.scriptdoc;

import colesico.framework.assist.StringUtils;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Typed identifier
 */
abstract public sealed class TypedId
        permits ClueSpec.Id,
        LocationSpec.Id,
        WaySpec.Id,
        PersonageSpec.Id,
        TickSpec.Id,
        DetectiveSpec.Id {

    public static final Pattern KEY_PATTERN = Pattern.compile("^([A-Z0-9]{1,3})$");

    /**
     * Id key
     */
    public final String key;

    protected TypedId(String key) {
        if (key == null || !KEY_PATTERN.matcher(key).matches()) {
            throw new IllegalArgumentException("Invalid TID key");
        }
        this.key = key;
    }

    abstract public Type type();

    public static TypedId parse(String tid) {
        if (StringUtils.isBlank(tid)) {
            throw new RuntimeException("Empty TID string");
        }
        Type type = Type.of(tid.charAt(0));
        if (type == null) {
            throw new RuntimeException("Unknown type for TID: " + tid);
        }
        String key = tid.substring(1);
        return switch (type) {
            case PERSONAGE -> new PersonageSpec.Id(key);
            case LOCATION -> new LocationSpec.Id(key);
            case WAY -> new WaySpec.Id(key);
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
        if (!(o instanceof TypedId tid)) return false;
        return Objects.equals(toString(), tid.toString());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(toString());
    }

    public enum Type {

        PERSONAGE('P'),
        LOCATION('L'),
        WAY('W'),
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
                case 'W' -> Type.WAY;
                case 'C' -> Type.CLUE;
                case 'T' -> Type.TIME;
                case 'D' -> Type.DETECTIVE;
                default -> throw new IllegalArgumentException("Undefined ID type code: " + code);
            };
        }

        @Override
        public final String toString() {
            return "" + code;
        }

    }
}
