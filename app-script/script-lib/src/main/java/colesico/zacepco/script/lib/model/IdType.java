package colesico.zacepco.script.lib.model;

public enum IdType {

    PERSONAGE('P'),
    LOCATION('L'),
    ARTIFACT('A'),
    TIME('T'),
    CUSTOM_ARTIFACT('C'),
    VIRTUAL_ARTIFACT('#');

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
            case 'A' -> IdType.ARTIFACT;
            case 'T' -> IdType.TIME;
            case 'C' -> IdType.CUSTOM_ARTIFACT;
            case '#' -> IdType.VIRTUAL_ARTIFACT;
            default -> null;
        };
    }
}
