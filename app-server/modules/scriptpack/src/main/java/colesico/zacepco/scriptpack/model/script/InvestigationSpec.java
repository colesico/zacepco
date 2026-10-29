package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;

import java.util.List;

/**
 * Investigation specification
 */
public class InvestigationSpec {

    /**
     * Investigation budget (resource)
     */
    @YamlComment(text = {"Investigation budget in resource units"})
    public Integer budget;

    /**
     * Randomness factor [1..0]. Used to recalculate table thresholds to change the randomness factor.
     * New_Threshold = Table_Threshold * randomnessFactor
     * Default = 1.0
     */
    @YamlComment(text = {
            "Randomness factor [1.0 ... 0.0]. Default = 1.0 ",
            "Used to recalculate investigation table thresholds to adjust randomness.",
            "New_threshold = Table_threshold * randFactor"
    })
    public Double randFactor;

    /**
     * Location search
     */
    @YamlComment(text = {
            "",
            "Location search specification",
            ""
    })
    public SearchSpec search;

    /**
     * Personage interrogation
     */
    @YamlComment(text = {
            "",
            "Personage interrogation specification",
            ""
    })
    public InterrogationSpec interrogation;


    @YamlComment(text = {
            "",
            "Criminal expertise specification",
            ""
    })
    public ExpertiseSpec expertise;

    @YamlComment(text = {
            "",
            "Detectives dictionary",
            "A subset of which can participate in the investigation",
            ""
    })
    public List<DetectiveSpec> detectives;

    public double randFactor() {
        return randFactor == null ? 1.0 : randFactor;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Double getRandFactor() {
        return randFactor;
    }

    public void setRandFactor(Double randFactor) {
        this.randFactor = randFactor;
    }

    public SearchSpec getSearch() {
        return search;
    }

    public void setSearch(SearchSpec search) {
        this.search = search;
    }

    public InterrogationSpec getInterrogation() {
        return interrogation;
    }

    public void setInterrogation(InterrogationSpec interrogation) {
        this.interrogation = interrogation;
    }

    public ExpertiseSpec getExpertise() {
        return expertise;
    }

    public void setExpertise(ExpertiseSpec expertise) {
        this.expertise = expertise;
    }

    public List<DetectiveSpec> getDetectives() {
        return detectives;
    }

    public void setDetectives(List<DetectiveSpec> detectives) {
        this.detectives = detectives;
    }
}
