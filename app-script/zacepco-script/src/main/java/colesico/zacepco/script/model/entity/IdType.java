package colesico.zacepco.script.model.entity;

public enum IdType {

    PERSONAGE('P'),
    LOCATION('L'),
    CLUE('С'),
    TIME('T');

    private final Character code;

    IdType(Character code) {
        this.code = code;
    }

    public Character code() {
        return code;
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

    @Override
    public String toString() {
        return "" + code;
    }
}
