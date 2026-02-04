package colesico.zacepco.script.lib.model;

/**
 * Script document
 */
public class Script {

    /**
     * Script document data
     */
    public ScriptHeader script;

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
    public Scene board;

    /**
     * Artifacts
     */
    public ArtifactDict artifacts;

    /**
     * Location search
     */
    public Search search;

    /**
     * Character interrogation
     */
    public Interrogation interrogation;

    /**
     * Clue expertise
     */
    public Expertise expertise;
}
