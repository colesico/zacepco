package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Interrogation specification
 */
public final class InterrogationSpec extends InvestigationActionSpec {

    @YamlComment(text = "Personages to be interrogated")
    public Map<PersonageSpec.Id, PersonageInterrogationSpec> personages = new LinkedHashMap<>();

    public InterrogationSpec() {
        this.cost = 2;
        this.points = 2;
        this.focusLimit = 2;
    }
}
