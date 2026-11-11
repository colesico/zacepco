package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlIgnore;

import java.util.LinkedHashMap;
import java.util.Map;

public class PersonageLogSpec {

    @YamlIgnore
    public PersonageSpec.Id personage;

    @YamlComment(text = {
            "Personage events for each tick"
    })
    public Map<TickSpec.Id, PersonageEventSpec> events = new LinkedHashMap<>();
}
