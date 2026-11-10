package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlCompact;

import java.util.*;

/**
 * Crime specification
 * Is a top-level element that encapsulates a specific crime scene environment,
 * its timeline, and all associated elements (the crime scene, characters, and clues)
 */
public final class CrimeSpec {

    @YamlComment(text = {
            "Crime timeline. Defines all possible ticks",
            "Example: T1: 10:00 or with date  T2: 2025-01-02T20:00",
            "Specify either the time only or the date-time only, mixing formats is not allowed."
    })
    @YamlCompact
    public Map<TickSpec.Id, TickSpec> timeline = new LinkedHashMap<>();

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
