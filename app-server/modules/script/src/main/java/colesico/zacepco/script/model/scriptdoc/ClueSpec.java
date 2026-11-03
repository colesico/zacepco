package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlCompact;

import java.util.ArrayList;
import java.util.List;

/**
 * Clue model
 */
public final class ClueSpec extends EntitySpec<ClueSpec.Id> {

    /**
     * False clue or not
     */
    @YamlComment(text = {
            "False clue or not",
            "Used as answers to verify the investigation's findings and results",
            "Default: false"
    })
    public Boolean falsity = false;

    /**
     * Entities to be open along with this clue
     */
    @YamlComment(text = {
            "Entities to be open along with this clue",
            "For example, this is a hidden location or a hidden personage"
    })
    @YamlCompact
    public List<UID> open = new ArrayList<>();

    @YamlComment(text = {
            "Target entities that this clue leads to",
            "Used as answers to verify the investigation results"
    })
    @YamlCompact
    public List<UID> leads = new ArrayList<>();

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
