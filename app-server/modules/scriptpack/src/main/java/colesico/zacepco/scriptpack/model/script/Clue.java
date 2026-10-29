package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;
import colesico.zacepco.scriptpack.yaml.YamlCompact;

import java.util.List;

/**
 * Clue model
 */
public class Clue extends Entity<Clue.Id> {

    /**
     * False clue or not
     */
    @YamlComment(text = {
            "False clue or not (default-false)",
            "Used as answers to verify the investigation's findings and results"
    })
    public Boolean falsity;

    /**
     * Entities to be open along with this clue
     */
    @YamlComment(text = {
            "Entities to be open along with this clue",
            "For example, this is a hidden location or a hidden personage"
    })
    @YamlCompact
    public List<UID> open;

    public boolean isFalsity() {
        return falsity != null ? falsity : false;
    }

    @Override
    public void setId(Id id) {
        super.setId(id);
    }

    public Boolean getFalsity() {
        return falsity;
    }

    public void setFalsity(Boolean falsity) {
        this.falsity = falsity;
    }

    public List<UID> getOpen() {
        return open;
    }

    public void setOpen(List<UID> open) {
        this.open = open;
    }

    public static final class Id extends UID {

        public Id(String key) {
            super(key);
        }

        @Override
        public Type type() {
            return Type.CLUE;
        }
    }
}
