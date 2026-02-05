package colesico.zacepco.script.lib.model;

public enum IdType {

    PERSONAGE('P'),
    LOCATION('L'),
    CLUE('С'),
    TIME('T');

    private final Character type;

    IdType(Character type) {
        this.type = type;
    }

    public Character code() {
        return type;
    }

    public static IdType of(char code) {
        return switch (code) {
            case 'P' -> IdType.PERSONAGE;
            case 'L' -> IdType.LOCATION;
            case 'C' -> IdType.CLUE;
            case 'T' -> IdType.TIME;
            default -> null;
        };
    }
}
