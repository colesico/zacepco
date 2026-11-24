package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;

/**
 * Inspection basics
 */
abstract public class InspectionSpec {

    @YamlComment(text = {
            "Cost in resource units of inspection on subject (int)"
    })
    public Integer cost = null;

    @YamlComment(text = {
            "Base points for inspection (int)"
    })
    public Integer points = null;

    @YamlComment(text = {
            "Maximum number of elements in detective focus"
    })
    public Integer focusLimit = null;

    @YamlComment(text = {
            "Cost of obtaining inspection subject statistics",
            "(-1) - statistics is prohibited",
            "(0) - free statistics",
            "Default:  0"
    })
    public Integer statCost = 0;

    public InspectionSpec() {
    }
}
