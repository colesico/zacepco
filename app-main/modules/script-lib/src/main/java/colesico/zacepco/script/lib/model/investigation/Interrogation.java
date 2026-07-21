package colesico.zacepco.script.lib.model.investigation;

import colesico.zacepco.script.lib.yaml.YamlComment;

import java.util.List;

/**
 * Interrogation specification
 */
public class Interrogation extends InvestigationAction {

    @YamlComment(text = "Personage to be interrogate")
    public List<PersonageInterrogation> personages;

    public List<PersonageInterrogation> getPersonages() {
        return personages;
    }

    public void setPersonages(List<PersonageInterrogation> personages) {
        this.personages = personages;
    }
}
