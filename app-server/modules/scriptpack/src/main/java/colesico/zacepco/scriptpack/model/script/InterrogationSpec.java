package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;

import java.util.List;

/**
 * Interrogation specification
 */
public class InterrogationSpec extends InvestigationActionSpec {

    @YamlComment(text = "Personage to be interrogated")
    public List<PersonageInterrogationSpec> personages;

    public List<PersonageInterrogationSpec> getPersonages() {
        return personages;
    }

    public void setPersonages(List<PersonageInterrogationSpec> personages) {
        this.personages = personages;
    }
}
