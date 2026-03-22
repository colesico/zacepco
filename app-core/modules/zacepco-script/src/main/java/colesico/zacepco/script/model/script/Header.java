package colesico.zacepco.script.model.script;

public class Header {

    /**
     * Script schema version (<a href="https://semver.org/">SemVer</a>)
     */
    public String schema;

    /**
     * Universally Unique Identifier
     */
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
