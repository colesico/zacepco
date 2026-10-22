package colesico.zacepco.casereg.srv.model;

public enum ScriptAssetType {

    IMG_SRC("thumb"),
    IMG_THUMB("thumb"),
    IMG_MEDIUM("medium");

    private final String resourceRoot;

    ScriptAssetType(String resourceRoot) {
        this.resourceRoot = resourceRoot;
    }

    public String resourceRoot() {
        return resourceRoot;
    }

    public static ScriptAssetType fromResource(String resource) {
        return switch (resource) {
            case "thumb" -> ScriptAssetType.IMG_THUMB;
            case "medium" -> ScriptAssetType.IMG_MEDIUM;
            default -> throw new IllegalStateException("Unexpected value: " + resource);
        };
    }

}
