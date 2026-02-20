package colesico.zacepco.script.model.script;

public class ScriptHeader {

    /**
     * Script schema version (1.. etc)
     */
    public Integer schema;

    /**
     * Script id
     */
    public String id;

    public Integer getSchema() {
        return schema;
    }

    public void setSchema(Integer schema) {
        this.schema = schema;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ScriptHeader{" +
                "schema=" + schema +
                ", id='" + id + '\'' +
                '}';
    }
}
