package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlIgnore;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Personage interrogation
 */
public final class PersonageInterrogationSpec {

    @YamlIgnore
    public PersonageSpec.Id personage;

    @YamlComment(text = "Target personage clues to be uncovered")
    public Map<ClueSpec.Id,FocusedClueSpec> clues = new LinkedHashMap<>();

}
