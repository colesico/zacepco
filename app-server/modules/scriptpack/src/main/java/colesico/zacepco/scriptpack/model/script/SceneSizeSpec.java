package colesico.zacepco.scriptpack.model.script;

import colesico.zacepco.scriptpack.yaml.YamlComment;

/**
 * Scene size
 *
 * @see LocationPosSpec
 */
public final class SceneSizeSpec {

    @YamlComment(text = "Rows number", inline = true)
    public Integer rows = 1;

    @YamlComment(text = "Columns number", inline = true)
    public Integer cols = 1;

}
