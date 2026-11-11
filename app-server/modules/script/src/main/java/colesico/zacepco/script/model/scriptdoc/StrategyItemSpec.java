package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlCompact;

import java.util.List;

public class StrategyItemSpec {

    @YamlComment(text = "Personage ID to interrogate")
    public PersonageSpec.Id personage;

    @YamlComment(text = "Location ID to search")
    public LocationSpec.Id location;

    @YamlComment(text = "Detective focus")
    @YamlCompact
    public List<TypedId> focus;

    @YamlComment(text = "Clue obtained")
    public ClueSpec.Id result;
}
