package colesico.zacepco.scriptpack.pkg;

public enum AssetType {
    PERSONAGE_IMG("personage/", ".png"),
    LOCATION_IMG("location/", ".png"),
    CLUE_IMG("clue/", ".png"),
    DETECTIVE_IMG("detective/", ".png");

    private final String pathPrefix;
    private final String pathSuffix;

    AssetType(String pathPrefix, String pathSuffix) {
        this.pathPrefix = pathPrefix;
        this.pathSuffix = pathSuffix;
    }

    public ResourcePath resourcePath(String assetId) {
        return ResourcePath.of(pathPrefix + assetId + pathSuffix);
    }
}
