package colesico.zacepco.script.lib.model;

/**
 * Script model
 */
public class Script {

    /**
     * Script model version (1.0.0... etc)
     */
    public String modelVersion;

    /**
     * Meta information
     */
    public MetaInf metainf;

    /**
     * Miscellaneous definitions
     */
    public ScriptMiscellaneous miscellaneous;

    /**
     * Characters
     */
    public ScriptPersonages personages;

    /**
     * Locations
     */
    public ScriptLocations locations;

    /**
     * Artifacts
     */
    public ScriptArtifacts artifacts;

    /**
     * Location search
     */
    public SearchDefinition search;

    /**
     * Character interrogation
     */
    public PersonageInterrogation interrogation;

    /**
     * Clue expertise
     */
    public Expertise expertise;
}
