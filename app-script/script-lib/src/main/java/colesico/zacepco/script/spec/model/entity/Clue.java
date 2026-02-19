package colesico.zacepco.script.spec.model.entity;

import java.util.List;

/**
 * Clue model
 */
public class Clue extends Entity<ClueId> {

    /**
     * False clue or not
     */
    public Boolean falsity;

    /**
     * Entities to be open along with this clue
     */
    public List<EntityId> open;

    public Boolean getFalsity() {
        return falsity;
    }

    public void setFalsity(Boolean falsity) {
        this.falsity = falsity;
    }

    public List<EntityId> getOpen() {
        return open;
    }

    public void setOpen(List<EntityId> open) {
        this.open = open;
    }
}
