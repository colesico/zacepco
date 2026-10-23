package colesico.zacepco.casereg.srv.model;

public enum ImageScale {

    ICON("icon", 64, 64),
    THUMB("thumb", 240, 240),
    MEDIUM("medium", 420, 420),
    FULL("full", 0, 0);

    private final String code;
    private final int width;
    private final int height;

    ImageScale(String code, int width, int height) {
        this.code = code;
        this.width = width;
        this.height = height;
    }

    public static ImageScale parse(String code) {
        return switch (code) {
            case "icon" -> ICON;
            case "thumb" -> THUMB;
            case "medium" -> MEDIUM;
            case "full" -> FULL;
            default -> null;
        };
    }

    public String code() {
        return code;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
}
