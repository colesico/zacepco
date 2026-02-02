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
    public MiscellaneousDefinition miscellaneous;

    /**
     * Characters
     */
    public PersonagesDefinition personages;

    /**
     * Locations
     */
    public LocationsDefinition locations;

    /**
     * Artifacts
     */
    public ArtifactsDefinition artifacts;

    /**
     * Location search
     */
    public SearchDefinition search;

    /**
     * Character interrogation
     */
    public InterrogationPersonage interrogation;

    /**
     * Clue expertise
     */
    public ExpertiseDefinition expertise;
}
