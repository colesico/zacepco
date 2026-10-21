package colesico.zacepco.respack.model.script;

import colesico.zacepco.respack.yaml.YamlComment;

import java.util.List;

/**
 * Clue model
 */
public class Clue extends Entity<Clue.Id> {

    /**
     * False clue or not
     */
    @YamlComment(text = "False clue or not (default-false)")
    public Boolean falsity;

    /**
     * Entities to be open along with this clue
     */
    @YamlComment(text = "Entities to be open along with this clue")
    public List<TypedId> open;

    public boolean isFalsity() {
        return falsity != null ? falsity : false;
    }

    public Boolean getFalsity() {
        return falsity;
    }

    public void setFalsity(Boolean falsity) {
        this.falsity = falsity;
    }

    public List<TypedId> getOpen() {
        return open;
    }

    public void setOpen(List<TypedId> open) {
        this.open = open;
    }

    public static final class Id extends TypedId {

        public Id(String key) {
            super(key);
        }

        @Override
        public Type type() {
            return null;
        }
    }
}
