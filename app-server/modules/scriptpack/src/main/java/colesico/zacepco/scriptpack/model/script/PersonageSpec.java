package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;
import colesico.zacepco.scriptpack.yaml.YamlCompact;

import java.util.ArrayList;
import java.util.List;

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

    @YamlComment(text = "Initially exposed clues for this personage")
    @YamlCompact
    public List<ClueSpec.Id> exposedClues = new ArrayList<>();

    /**
     * Personage-entity interaction for each time tick.
     */
    @YamlComment(text = {
            "Personage event log for each time tick",
            "Each element of the array corresponds to a time tick, starting from 1.",
            "Used to verify the investigation's findings (answers)"
    })
    public List<PersonageEventSpec> log = new ArrayList<>();

    public static final class Id extends UID {

        @Override
        public Type type() {
            return Type.PERSONAGE;
        }

        public Id(String value) {
            super(value);
        }
    }
}
