package colesico.zacepco.script.lib.model;

/**
 * Script model
 */
public class Script {

    /**
     * Script model version (1.0.0... etc)
     */
    public String script;

    /**
     * Script common information
     */
    public MetaInf metainf;

    /**
     * Miscellaneous definitions
     */
    public ScriptMiscellaneous miscellaneous;

    /**
     * Characters definitions
     */
    public ScriptPersonages personages;

    /**
     * Locations definition
     */
    public ScriptLocations locations;

    /**
     * Artifacts definitions
     */
    public ScriptArtifacts artifacts;

    /**
     * Location search definitions
     */
    public LocationSearch search;

    /**
     * Character interrogation
     */
    public PersonageInterrogation interrogation;
}
