package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlCompact;

import java.util.*;

/**
 * Clue model
 */
@YamlComment(text = {
        "Unique clue ID",
        "Format: C[A-Z0-9]{1,3}"
}, field = "id")
public final class ClueSpec extends EntitySpec<ClueSpec.Id> {

    @YamlComment(text = {
            "Initially discovered clue or not",
            "Default: false"
    })
    public Boolean discovered = false;

    @YamlComment(text = {
            "False clue or not",
            "Default: false",
            "Used as answers to verify the investigation's findings"
    })
    @YamlCompact
    public Boolean falsity = false;

    @YamlComment(text = {
            "Entities that this clue uncovers (characters, locations, other clues)"
    })
    @YamlCompact
    public Set<TId> uncovers = new LinkedHashSet<>();

    @YamlComment(text = {
            "Target entities that this clue leads to",
            "Used as answers to verify the investigation results"
    })
    @YamlCompact
    public Set<TId> leadsTo = new LinkedHashSet<>();

    public static final class Id extends TId {

        public Id(String key) {
            super(key);
        }

        @Override
        public Type type() {
            return Type.CLUE;
        }
    }
}
