package colesico.zacepco.script.model.investigation;

import colesico.zacepco.script.yaml.YamlComment;

/**
 * Investigation specification
 */
public class Investigation {

    /**
     * Investigation budget (resource)
     */
    @YamlComment("Investigation budget in resource units")
    public Integer budget;

    /**
     * Randomness factor [1..0]. Used to recalculate table thresholds to change the randomness factor.
     * New_Threshold = Table_Threshold/(12 * randomnessFactor)
     * Default = 1
     */
    @YamlComment("Randomness factor [1..0].\n" +
            "Used to recalculate table thresholds to adjust randomness.\n" +
            "New_threshold = Table_threshold/(12 * randomnessFactor) Default = 1")
    public Double randFactor;

    /**
     * Location search
     */
    @YamlComment("Location search specification")
    public Search search;

    /**
     * Personage interrogation
     */
    @YamlComment("Personage interrogation specification")
    public Interrogation interrogation;

    /**
     * Forensic laboratory examination
     */
    @YamlComment("Forensic laboratory examination specification")
    public Interrogation expertise;
}
