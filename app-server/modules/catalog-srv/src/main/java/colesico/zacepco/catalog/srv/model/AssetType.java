package colesico.zacepco.catalog.srv.model;

public enum AssetType {

    THUMB("thumb"),
    MEDIUM("medium");

    private final String resource;

    AssetType(String resource) {
        this.resource = resource;
    }

    public String resourceDir() {
        return resource;
    }

    public static AssetType fromType(String resource) {
        return switch (resource) {
            case "thumb" -> AssetType.THUMB;
            case "medium" -> AssetType.MEDIUM;
            default -> throw new IllegalStateException("Unexpected value: " + resource);
        };
    }

}
