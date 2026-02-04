package colesico.zacepco.script.lib.model;

import java.util.List;

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
    public List<Personage> personages;

    /**
     * Locations graph definition
     */
    public List<Location> locations;

    /**
     * Clues
     */
    public List<Clue> clues;

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
