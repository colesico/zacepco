package colesico.zacepco.script.model.investigation;

import colesico.zacepco.script.yaml.YamlComment;

/**
 * Investigation specification
 */
public class Investigation {

    /**
     * Investigation budget (resource)
     */
    @YamlComment(text = {"Investigation budget in resource units"})
    public Integer budget;

    /**
     * Randomness factor [1..0]. Used to recalculate table thresholds to change the randomness factor.
     * New_Threshold = Table_Threshold/(12 * randomnessFactor)
     * Default = 1
     */
    @YamlComment(text = {
            "Randomness factor [1..0].",
            "Used to recalculate investigation table thresholds to adjust randomness.",
            "New_threshold = Table_threshold/(12 * randFactor) Default = 1"
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
    public Search search;

    /**
     * Personage interrogation
     */
    @YamlComment(text = {
            "",
            "Personage interrogation specification",
            ""
    })
    public Interrogation interrogation;

    /**
     * Forensic laboratory examination
     */
    @YamlComment(text = {
            "",
            "Forensic laboratory examination specification",
            ""
    })
    public Expertise expertise;

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

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public Interrogation getInterrogation() {
        return interrogation;
    }

    public void setInterrogation(Interrogation interrogation) {
        this.interrogation = interrogation;
    }

    public Expertise getExpertise() {
        return expertise;
    }

    public void setExpertise(Expertise expertise) {
        this.expertise = expertise;
    }
}
