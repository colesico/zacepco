package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlCompact;

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


    public static final class Id extends TypedId {

        @Override
        public Type type() {
            return Type.PERSONAGE;
        }

        public Id(String value) {
            super(value);
        }
    }
}
