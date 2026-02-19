package colesico.zacepco.script.lib.model.script;

import colesico.zacepco.script.lib.model.entity.Clue;
import colesico.zacepco.script.lib.model.entity.Personage;
import colesico.zacepco.script.lib.model.entity.Scene;
import colesico.zacepco.script.lib.model.inestigation.Expertise;
import colesico.zacepco.script.lib.model.inestigation.Interrogation;
import colesico.zacepco.script.lib.model.inestigation.Search;

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
     * Characters dictionary
     */
    public List<Personage> personages;

    /**
     * Scene
     */
    public Scene scene;

    /**
     * Clues dictionary
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
