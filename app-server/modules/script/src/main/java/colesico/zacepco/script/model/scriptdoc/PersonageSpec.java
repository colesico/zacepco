package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlCompact;

import java.util.*;

/**
 * Game character
 */
public final class PersonageSpec extends EntitySpec<PersonageSpec.Id> {

    /**
     * Hidden personage or not
     */
    @YamlComment(text = {
            "Initially hidden personage or not",
            "Default: false"
    })
    public Boolean hidden = false;

    @YamlComment(text = "Personage related clues")
    @YamlCompact
    public Set<ClueSpec.Id> clues = new LinkedHashSet<>();

    @YamlComment(text = {
            "Personage log for each tick",
            "Used to verify the investigation's findings (answers)"
    })
    public Map<TickSpec.Id, PersonageEventSpec> log = new LinkedHashMap<>();

    public static final class Id extends TId {

        @Override
        public Type type() {
            return Type.PERSONAGE;
        }

        public Id(String value) {
            super(value);
        }
    }
}
