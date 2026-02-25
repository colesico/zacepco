package colesico.zacepco.script.model.entity;

public enum EntityType {

    PERSONAGE('P'),
    LOCATION('L'),
    CLUE('С'),
    TIME('T');

    private final Character code;

    EntityType(Character code) {
        this.code = code;
    }

    public Character code() {
        return code;
    }

    public static EntityType of(char code) {
        return switch (code) {
            case 'P' -> EntityType.PERSONAGE;
            case 'L' -> EntityType.LOCATION;
            case 'C' -> EntityType.CLUE;
            case 'T' -> EntityType.TIME;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return "" + code;
    }
}
