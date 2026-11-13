package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;

/**
 * Scene size
 *
 * @see LocationPosSpec
 */
public final class SceneSizeSpec {

    @YamlComment(text = "Columns number", inline = true)
    public Integer cols = 1;

    @YamlComment(text = "Rows number", inline = true)
    public Integer rows = 1;


}
