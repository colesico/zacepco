package colesico.zacepco.script.lib.model.setting;

import colesico.zacepco.script.lib.yaml.YamlComment;

import java.util.List;

/**
 * Clue model
 */
public class Clue extends Entity<ClueId> {

    /**
     * False clue or not
     */
    @YamlComment(text = "False clue or not (default-false)")
    public Boolean falsity;

    /**
     * Entities to be open along with this clue
     */
    @YamlComment(text = "Entities to be open along with this clue")
    public List<EntityId> open;

    public boolean falsity() {
        return falsity != null ? falsity : false;
    }

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
