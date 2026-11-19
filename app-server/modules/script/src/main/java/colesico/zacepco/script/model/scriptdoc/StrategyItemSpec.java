package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlCompact;

import java.util.List;
import java.util.Set;

public class StrategyItemSpec {

    @YamlComment(text = "Personage ID to interrogate")
    public PersonageSpec.Id personage;

    @YamlComment(text = "Location ID to search")
    public LocationSpec.Id location;

    @YamlComment(text = "Detective focus")
    @YamlCompact
    public Set<TId> focus;

    @YamlComment(text = "Clue obtained")
    public ClueSpec.Id result;
}
