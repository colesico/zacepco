package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;

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
     * Randomness factor [1..0]. Used to recalculate table thresholds to change the randomness factor.
     * New_Threshold = Table_Threshold * randomnessFactor
     * Default = 1.0
     */
    @YamlComment(text = {
            "Randomness factor [1.0 ... 0.0]",
            "Used to recalculate investigation table thresholds to adjust randomness.",
            "New_threshold = Table_threshold * randFactor",
            "Default: 1.0"
    })
    public Double randFactor = 1.0;

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

    public double randFactor(Double value) {
        if (value != null) {
            if (value < 0.0 || value > 1.0) {
                throw new RuntimeException("Invalid randFactor value: " + value);
            }
            return value;
        }
        return randFactor == null ? 1.0 : randFactor;
    }

}
