package colesico.zacepco.cases.srv.model;

public enum ScriptAssetType {

    THUMB("thumb"),
    MEDIUM("medium");

    private final String resource;

    ScriptAssetType(String resource) {
        this.resource = resource;
    }

    public String resourceDir() {
        return resource;
    }

    public static ScriptAssetType fromType(String resource) {
        return switch (resource) {
            case "thumb" -> ScriptAssetType.THUMB;
            case "medium" -> ScriptAssetType.MEDIUM;
            default -> throw new IllegalStateException("Unexpected value: " + resource);
        };
    }

}
