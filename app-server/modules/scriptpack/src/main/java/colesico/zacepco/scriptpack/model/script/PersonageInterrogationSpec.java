package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;

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
