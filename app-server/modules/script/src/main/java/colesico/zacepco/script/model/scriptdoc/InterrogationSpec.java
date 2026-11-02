package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;

import java.util.ArrayList;
import java.util.List;

/**
 * Interrogation specification
 */
public final class InterrogationSpec extends InvestigationActionSpec {

    @YamlComment(text = "Personage to be interrogated")
    public List<PersonageInterrogationSpec> personages = new ArrayList<>();

}
