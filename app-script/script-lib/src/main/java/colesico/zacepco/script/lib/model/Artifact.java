package colesico.zacepco.script.lib.model;

import java.util.Map;

public class Artifact {

    public EntityId id;

    public String name;

    public String description;

    public Map<String,EntityId> params;

    /**
     * Is artifact unrelated to the crime
     */
    public Boolean irrelevant;

}
