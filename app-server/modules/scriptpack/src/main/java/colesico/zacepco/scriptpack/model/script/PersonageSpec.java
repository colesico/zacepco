package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;
import colesico.zacepco.scriptpack.yaml.YamlCompact;

import java.util.List;

/**
 * Game character
 */
public class PersonageSpec extends EntitySpec<PersonageSpec.Id> {

    /**
     * Hidden personage or not
     */
    @YamlComment(text = "Initially hidden personage or not (default - false)")
    public Boolean hidden;

    @YamlComment(text = "Initially exposed clues for this personage")
    @YamlCompact
    public List<ClueSpec.Id> exposedClues;

    /**
     * Personage-entity interaction for each time tick.
     */
    @YamlComment(text = {
            "Personage event log for each time tick",
            "Each element of the array corresponds to a time tick, starting from 1.",
            "Used to verify the investigation's findings (answers)"
    })
    public List<PersonageEventSpec> log;

    public boolean hidden() {
        return hidden != null ? hidden : false;
    }

    @Override
    public void setId(Id id) {
        super.setId(id);
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public List<PersonageEventSpec> getLog() {
        return log;
    }

    public void setLog(List<PersonageEventSpec> log) {
        this.log = log;
    }

    public List<ClueSpec.Id> getExposedClues() {
        return exposedClues;
    }

    public void setExposedClues(List<ClueSpec.Id> exposedClues) {
        this.exposedClues = exposedClues;
    }

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
