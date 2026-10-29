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
public class CrimeSpec {

    @YamlComment(text = {
            "Crime timeline. Defines possible ticks",
            "Timestamp format example: 10:00 or with date 2025-01-02T20:00",
            "Specify either the time only or the date-time only",
            "Mixing formats is not allowed"
    })
    public List<TickSpec> timeline;

    /**
     * Characters dictionary
     */
    @YamlComment(text = {
            "",
            "Personages dictionary.",
            "Defines all personages involved in the crime",
            ""
    })
    public List<PersonageSpec> personages;

    /**
     * Scene
     */
    @YamlComment(text = {
            "",
            "Crime scene definition",
            ""
    })
    public SceneSpec scene;

    /**
     * Clues dictionary
     */
    @YamlComment(text = {
            "",
            "Clues dictionary",
            ""
    })
    public List<ClueSpec> clues;

    public Optional<PersonageSpec> findPersonageById(PersonageSpec.Id id) {
        return personages.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public Optional<ClueSpec> findClueById(ClueSpec.Id id) {
        return clues.stream()
                .filter(cl -> cl.getId().equals(id))
                .findFirst();
    }

    public List<TickSpec> getTimeline() {
        return timeline;
    }

    public void setTimeline(List<TickSpec> timeline) {
        this.timeline = timeline;
    }

    public List<PersonageSpec> getPersonages() {
        return personages;
    }

    public void setPersonages(List<PersonageSpec> personages) {
        this.personages = personages;
    }

    public SceneSpec getScene() {
        return scene;
    }

    public void setScene(SceneSpec scene) {
        this.scene = scene;
    }

    public List<ClueSpec> getClues() {
        return clues;
    }

    public void setClues(List<ClueSpec> clues) {
        this.clues = clues;
    }

}
