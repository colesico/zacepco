package colesico.zacepco.script.spec.model.script;

import colesico.zacepco.script.spec.model.entity.Clue;
import colesico.zacepco.script.spec.model.entity.Personage;
import colesico.zacepco.script.spec.model.entity.Scene;
import colesico.zacepco.script.spec.model.inestigation.Expertise;
import colesico.zacepco.script.spec.model.inestigation.Interrogation;
import colesico.zacepco.script.spec.model.inestigation.Search;

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

    /**
     * General answers
     */
    public Answers answers;

    public ScriptHeader getScript() {
        return script;
    }

    public void setScript(ScriptHeader script) {
        this.script = script;
    }

    public Metadata getMeta() {
        return meta;
    }

    public void setMeta(Metadata meta) {
        this.meta = meta;
    }

    public Miscellaneous getMisc() {
        return misc;
    }

    public void setMisc(Miscellaneous misc) {
        this.misc = misc;
    }

    public List<Personage> getPersonages() {
        return personages;
    }

    public void setPersonages(List<Personage> personages) {
        this.personages = personages;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public List<Clue> getClues() {
        return clues;
    }

    public void setClues(List<Clue> clues) {
        this.clues = clues;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public Interrogation getInterrogation() {
        return interrogation;
    }

    public void setInterrogation(Interrogation interrogation) {
        this.interrogation = interrogation;
    }

    public Expertise getExpertise() {
        return expertise;
    }

    public void setExpertise(Expertise expertise) {
        this.expertise = expertise;
    }

    public Answers getAnswers() {
        return answers;
    }

    public void setAnswers(Answers answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Script{" +
                "script=" + script +
                '}';
    }
}
