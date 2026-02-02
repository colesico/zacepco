package colesico.zacepco.script.lib.model;

public enum IdCode {

    PERSONAGE('P'),
    LOCATION('L'),
    ARTIFACT('A'),
    TIME('T'),
    CUSTOM_ARTIFACT('C'),
    VIRTUAL_ARTIFACT('#');

    private final Character code;

    IdCode(Character code) {
        this.code = code;
    }

    public Character code() {
        return code;
    }

    public static IdCode fromCode(char code) {
        return switch (code) {
            case 'P' -> IdCode.PERSONAGE;
            case 'L' -> IdCode.LOCATION;
            case 'A' -> IdCode.ARTIFACT;
            case 'T' -> IdCode.TIME;
            case 'C' -> IdCode.CUSTOM_ARTIFACT;
            case '#' -> IdCode.VIRTUAL_ARTIFACT;
            default -> null;
        };
    }
}
