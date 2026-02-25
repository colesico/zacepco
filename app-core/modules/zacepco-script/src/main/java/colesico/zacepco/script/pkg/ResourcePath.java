package colesico.zacepco.script.pkg;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Package resource path
 */
public final class ResourcePath {

    private final String value;

    private ResourcePath(String value) {
        this.value = value;
    }

    public static ResourcePath of(String path) {
        return new ResourcePath(Paths.get(path).normalize().toString());
    }

    public String value() {
        return value;
    }

    public Path path() {
        return Paths.get(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ResourcePath that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
