package colesico.zacepco.script.lib.model.interrogation;

import java.util.List;

/**
 * Interrogation specification
 */
public class Interrogation {
    public List<InterrogationPersonage> personages;

    public List<InterrogationPersonage> getPersonages() {
        return personages;
    }

    public void setPersonages(List<InterrogationPersonage> personages) {
        this.personages = personages;
    }
}
