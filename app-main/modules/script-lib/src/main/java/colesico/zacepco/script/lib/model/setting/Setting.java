package colesico.zacepco.script.lib.model.setting;

import colesico.zacepco.script.lib.yaml.YamlComment;

import java.util.List;

/**
 * Crime setting specification
 * Is a top-level element that encapsulates a specific crime scene environment,
 * its timeline, and all associated elements (the crime scene, characters, and clues)
 */
public class Setting {

    /**
     * Crime duration in ticks
     */
    @YamlComment(text = "Crime duration in ticks")
    public Integer crimeDuration;

    /**
     * Characters dictionary
     */
    @YamlComment(text = {
            "",
            "Personages dictionary.",
            "Defines all personages involved in the crime",
            ""
    })
    public List<Personage> personages;

    /**
     * Scene
     */
    @YamlComment(text = {
            "",
            "Crime scene definition",
            ""
    })
    public Scene scene;

    /**
     * Clues dictionary
     */
    @YamlComment(text = {
            "",
            "Clues dictionary",
            ""
    })
    public List<Clue> clues;

    public Integer getCrimeDuration() {
        return crimeDuration;
    }

    public void setCrimeDuration(Integer crimeDuration) {
        this.crimeDuration = crimeDuration;
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
}
