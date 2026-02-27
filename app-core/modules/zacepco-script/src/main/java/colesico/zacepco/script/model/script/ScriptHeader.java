package colesico.zacepco.script.model.script;

public class ScriptHeader {

    /**
     * Script schema version (https://semver.org/)
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
        return "ScriptHeader{" +
                "schema=" + schema +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
