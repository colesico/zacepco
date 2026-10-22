package colesico.zacepco.casereg.srv.model;

public enum ScriptAssetType {

    IMG_THUMB("thumb"),
    IMG_MEDIUM("medium");

    private final String resource;

    ScriptAssetType(String resource) {
        this.resource = resource;
    }

    public String resourceDir() {
        return resource;
    }

    public static ScriptAssetType fromType(String resource) {
        return switch (resource) {
            case "thumb" -> ScriptAssetType.IMG_THUMB;
            case "medium" -> ScriptAssetType.IMG_MEDIUM;
            default -> throw new IllegalStateException("Unexpected value: " + resource);
        };
    }

}
