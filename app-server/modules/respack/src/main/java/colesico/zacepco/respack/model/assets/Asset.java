package colesico.zacepco.respack.model.assets;

import colesico.zacepco.respack.yaml.YamlComment;

import java.util.regex.Pattern;

/**
 * Abstract asset
 */
abstract public class Asset {

    public static final Pattern ID_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{1,32}$");

    @YamlComment(text = {
            "Assets Id",
            "Only alphanumeric or underscores, length 1-32 chars"
    })
    private String id;

    public void setId(String id) {
        if (id == null || !ID_PATTERN.matcher(id).matches()) {
            throw new IllegalArgumentException("Invalid ID value. Only alphanumeric or underscores, length 1-32 chars");
        }
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
