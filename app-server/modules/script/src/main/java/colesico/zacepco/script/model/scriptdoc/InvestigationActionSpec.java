package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;

/**
 * Investigation action basics
 */
abstract public class InvestigationActionSpec {

    /**
     * Cost of investigation action on subject
     */
    @YamlComment(text = {
            "Cost in resource units of investigation action on subject (int)"
    })
    public Integer cost = null;

    /**
     * Base points for investigation action
     */
    @YamlComment(text = {
            "Base points for investigation action (int)"
    })
    public Integer points = null;

    /**
     * Maximum number of elements in detective focus
     */
    @YamlComment(text = {
            "Maximum number of elements in detective focus"
    })
    public Integer focusLimit = null;

    /**
     * Cost of obtaining investigation subject statistics
     */
    @YamlComment(text = {
            "Cost of obtaining investigation subject statistics",
            "(-1) - statistics is prohibited",
            "(0) - free statistics",
            "Default:  0"
    })
    public Integer statCost = 0;

    public InvestigationActionSpec() {
    }
}
