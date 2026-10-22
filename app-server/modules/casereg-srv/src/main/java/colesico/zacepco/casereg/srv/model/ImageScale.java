package colesico.zacepco.casereg.srv.model;

public enum ImageScale {

    THUMB("thumb", 240, 240),
    MEDIUM("medium", 420, 420);

    private final String storageRoot;
    private final int width;
    private final int height;

    ImageScale(String storageRoot, int width, int height) {
        this.storageRoot = storageRoot;
        this.width = width;
        this.height = height;
    }

    public String storageRoot() {
        return storageRoot;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
}
