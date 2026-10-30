package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;

/**
 * Investigation action basics
 */
abstract public class InvestigationActionSpec {

    /**
     * Cost of investigation action on subject
     */
    @YamlComment(text = {
            "Cost in resource units of investigation action on subject (int)",
            "Default: 1"
    })
    public Integer cost = 1;

    /**
     * Base points for investigation action
     */
    @YamlComment(text = {
            "Base points for investigation action (int)",
            "Default: 1"
    })
    public Integer points = 1;

    /**
     * Maximum number of elements in detective focus
     */
    @YamlComment(text = {
            "Maximum number of elements in detective focus",
            "Default: 3"
    })
    public Integer focusLimit = 3;

    /**
     * Cost of obtaining investigation subject statistics
     * (-1) - statistics is prohibited
     */
    @YamlComment(text = {
            "Cost of obtaining investigation subject statistics",
            "(-1) - statistics is prohibited",
            "(0) - free statistics",
            "Default:  0"
    })
    public Integer statCost = 0;

}
