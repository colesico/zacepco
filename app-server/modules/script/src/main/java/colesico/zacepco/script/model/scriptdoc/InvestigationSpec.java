package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlCompact;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Investigation specification
 */
public final class InvestigationSpec {

    /**
     * Investigation budget (resource)
     */
    @YamlComment(text = {
            "Investigation budget in resource units",
            "Default: 2147483647"
    })
    public Integer budget = Integer.MAX_VALUE;

    @YamlComment(text = {
            "Entities to be reveal (open) along with given clue",
            "For example, C1 reveals a hidden way or a hidden personage"
    })
    @YamlCompact
    public Map<ClueSpec.Id, List<TId>> revealChain = new LinkedHashMap<>();

    /**
     * Location search
     */
    @YamlComment(text = {
            "",
            "Location search specification",
            ""
    })
    public SearchSpec search = new SearchSpec();

    /**
     * Personage interrogation
     */
    @YamlComment(text = {
            "",
            "Personage interrogation specification",
            ""
    })
    public InterrogationSpec interrogation = new InterrogationSpec();


    @YamlComment(text = {
            "",
            "Criminal expertise specification",
            ""
    })
    public ExpertiseSpec expertise = new ExpertiseSpec();

    @YamlComment(text = {
            "",
            "Detectives dictionary",
            "A subset of which can participate in the investigation",
            ""
    })
    public Map<DetectiveSpec.Id, DetectiveSpec> detectives = new LinkedHashMap<>();

}
