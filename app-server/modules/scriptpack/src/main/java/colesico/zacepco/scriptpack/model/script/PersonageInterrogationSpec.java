package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;

import java.util.List;

/**
 * Personage interrogation
 */
public class PersonageInterrogationSpec {

    /**
     * Personage Id
     */
    @YamlComment(text = "Personage ID")
    public PersonageSpec.Id personage;

    @YamlComment(text = "Personage clues")
    public List<FocusedClueSpec> clues;

    public PersonageSpec.Id getPersonage() {
        return personage;
    }

    public void setPersonage(PersonageSpec.Id personage) {
        this.personage = personage;
    }

    public List<FocusedClueSpec> getClues() {
        return clues;
    }

    public void setClues(List<FocusedClueSpec> clues) {
        this.clues = clues;
    }
}
