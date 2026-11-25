package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlCompact;

import java.util.Set;

public class StrategyItemSpec {

    @YamlComment(text = {
            "Inspection type",
            "Possible values: SEARCH, INTERROGATION, EXAMINATION"
    })
    public InspectionTypeSpec inspection;

    @YamlComment(text = "Entity ID to inspect")
    public TId target;

    @YamlComment(text = "Detective focus")
    @YamlCompact
    public Set<TId> focus;

    @YamlComment(text = "Clue obtained")
    public ClueSpec.Id result;
}
