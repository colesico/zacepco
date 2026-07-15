package colesico.zacepco.script.model.setting;

import java.util.List;

/**
 * Personage-entity interaction log.
 * Contains IDs of entities interacted with by personage per tick.
 * Used to verify the investigation's findings.
 */
public class PersonageInteraction {
    /**
     * The location the character was inside.
     */
    public LocationId location;

    /**
     * Personages with whom this character interacted.
     * For example: saw, said something, transferred, received, etc.
     */
    public List<PersonageId> personages;

    /**
     * Personages with whom this character interacted.
     * For example: saw, said something, transferred, received, etc.
     */
    public List<ClueId> clues;

    public LocationId getLocation() {
        return location;
    }

    public void setLocation(LocationId location) {
        this.location = location;
    }

    public List<PersonageId> getPersonages() {
        return personages;
    }

    public void setPersonages(List<PersonageId> personages) {
        this.personages = personages;
    }

    public List<ClueId> getClues() {
        return clues;
    }

    public void setClues(List<ClueId> clues) {
        this.clues = clues;
    }
}
