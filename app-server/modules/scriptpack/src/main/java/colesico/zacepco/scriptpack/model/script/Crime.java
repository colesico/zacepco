package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;
import colesico.zacepco.scriptpack.yaml.YamlCompact;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Crime setting specification
 * Is a top-level element that encapsulates a specific crime scene environment,
 * its timeline, and all associated elements (the crime scene, characters, and clues)
 */
public class Crime {

    @YamlComment(text = {
            "Crime timeline",
            "Timestamps for each tick T1..Tn",
            "Format example: 10:00 or with date 2025-01-02T20:00",
            "Specify either the time only or the date-time only",
            "Mixing formats is not allowed"
    })
    @YamlCompact
    public List<LocalDateTime> timeline;

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

    public Optional<Personage> findPersonageById(Personage.Id id) {
        return personages.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public Optional<Clue> findClueById(Clue.Id id) {
        return clues.stream()
                .filter(cl -> cl.getId().equals(id))
                .findFirst();
    }

    public List<LocalDateTime> getTimeline() {
        return timeline;
    }

    public void setTimeline(List<LocalDateTime> timeline) {
        this.timeline = timeline;
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
