package colesico.zacepco.catalog.srv.model;

public enum AssetType {

    THUMB("thumb"),
    MEDIUM("medium");

    private final String resourceDir;

    AssetType(String resourceDir) {
        this.resourceDir = resourceDir;
    }

    public String resourceDir() {
        return resourceDir;
    }
}
