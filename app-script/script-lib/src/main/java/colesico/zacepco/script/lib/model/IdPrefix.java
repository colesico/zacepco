package colesico.zacepco.script.lib.model;

public enum IdPrefix {

    PERSONAGE('P'),
    LOCATION('L'),
    GENERAL_ARTIFACT('A'),
    CUSTOM_ARTEFACT('C'),
    TIME('T');

    private Character prefix;

    IdPrefix(Character prefix) {
        this.prefix = prefix;
    }

    public Character getPrefix() {
        return prefix;
    }
}
