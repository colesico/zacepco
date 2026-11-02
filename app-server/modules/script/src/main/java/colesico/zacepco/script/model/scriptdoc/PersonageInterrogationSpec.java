package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;

import java.util.ArrayList;
import java.util.List;

/**
 * Personage interrogation
 */
public final class PersonageInterrogationSpec {

    /**
     * Personage Id
     */
    @YamlComment(text = "Personage ID")
    public PersonageSpec.Id personage;

    @YamlComment(text = "Personage clues")
    public List<FocusedClueSpec> clues = new ArrayList<>();

}
