package colesico.zacepco.script.model.script;

import colesico.zacepco.script.yaml.YamlComment;

public class Header {

    /**
     * Script schema version (<a href="https://semver.org/">SemVer</a>)
     */
    @YamlComment("Script schema version (data model version)")
    public String schema;

    /**
     * Universally Unique Identifier
     */
    @YamlComment("Universally unique identifier (UUID) of the script")
    public String uuid;

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Header{" +
                "schema=" + schema +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
