package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;
import colesico.zacepco.scriptpack.yaml.YamlCompact;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Crime setting specification
 * Is a top-level element that encapsulates a specific crime scene environment,
 * its timeline, and all associated elements (the crime scene, characters, and clues)
 */
public final class CrimeSpec {

    @YamlComment(text = {
            "Crime timeline. Defines possible ticks",
            "Timestamp format example: 10:00 or with date 2025-01-02T20:00",
            "Specify either the time only or the date-time only",
            "Mixing formats is not allowed"
    })
    public List<TickSpec> timeline = new ArrayList<>();

    /**
     * Characters dictionary
     */
    @YamlComment(text = {
            "",
            "Personages dictionary.",
            "Defines all personages involved in the crime",
            ""
    })
    public List<PersonageSpec> personages = new ArrayList<>();

    /**
     * Scene
     */
    @YamlComment(text = {
            "",
            "Crime scene definition",
            ""
    })
    public SceneSpec scene = new SceneSpec();

    /**
     * Clues dictionary
     */
    @YamlComment(text = {
            "",
            "Clues dictionary",
            ""
    })
    public List<ClueSpec> clues = new ArrayList<>();

    public Optional<PersonageSpec> findPersonageById(PersonageSpec.Id id) {
        return personages.stream()
                .filter(user -> user.id.equals(id))
                .findFirst();
    }

    public Optional<ClueSpec> findClueById(ClueSpec.Id id) {
        return clues.stream()
                .filter(cl -> cl.id.equals(id))
                .findFirst();
    }

}
