package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;

import java.util.ArrayList;
import java.util.List;

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
    public List<DetectiveSpec> detectives = new ArrayList<>();

}
