package colesico.zacepco.script.lib.model;

/**
 * Script document
 */
public class Script {

    /**
     * Meta information
     */
    public Metadata meta;

    /**
     * Miscellaneous definitions
     */
    public Miscellaneous misc;

    /**
     * Characters
     */
    public PersonageDict personages;

    /**
     * Locations
     */
    public LocationDict locations;

    /**
     * Artifacts
     */
    public ArtifactDict artifacts;

    /**
     * Location search
     */
    public SearchSpec search;

    /**
     * Character interrogation
     */
    public InterrogationSpec interrogation;

    /**
     * Clue expertise
     */
    public ExpertiseSpec expertise;
}
