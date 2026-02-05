package colesico.zacepco.script.lib.model;

import java.util.Map;

/**
 * Artifact model
 */
public class Clue {

    public EntityId id;

    /**
     * Clue short name
     */
    public String name;

    /**
     * Clue detailed description
     */
    public String details;

    /**
     * Artifact parameters
     */
    public Map<String,EntityId> params;

    /**
     * False artifact - unrelated to the crime
     */
    public Boolean unrelated;

}
