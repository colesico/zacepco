package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlIgnore;

import java.util.Map;

public class PersonageLogSpec {

    @YamlIgnore
    public PersonageSpec.Id personage;

    @YamlComment(text = {
            "Event log for each tick"
    })
    public Map<TickSpec.Id, PersonageEventSpec> events;
}
