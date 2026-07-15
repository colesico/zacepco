package colesico.zacepco.script.model.setting;

import colesico.zacepco.script.yaml.YamlComment;

import java.util.List;

/**
 * Game character
 */
public class Personage extends Entity<PersonageId> {

    /**
     * Hidden personage or not
     */
    @YamlComment("Hidden personage or not")
    public Boolean hidden;

    /**
     * Personage-entity interaction for each time tick.
     */
    @YamlComment("Personage-entity interaction for each time tick\n" +
            "Used to verify the investigation's findings")
    public List<PersonageInteraction> interaction;

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public List<PersonageInteraction> getInteraction() {
        return interaction;
    }

    public void setInteraction(List<PersonageInteraction> interaction) {
        this.interaction = interaction;
    }
}
