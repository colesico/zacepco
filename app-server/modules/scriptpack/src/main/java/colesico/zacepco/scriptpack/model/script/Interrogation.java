package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;

import java.util.List;

/**
 * Interrogation specification
 */
public class Interrogation extends InvestigationAction {

    @YamlComment(text = "Personage to be interrogated")
    public List<PersonageInterrogation> personages;

    public List<PersonageInterrogation> getPersonages() {
        return personages;
    }

    public void setPersonages(List<PersonageInterrogation> personages) {
        this.personages = personages;
    }
}
