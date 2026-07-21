package colesico.zacepco.script.lib.model.setting;

import colesico.zacepco.script.lib.yaml.YamlComment;

/**
 * Scene size
 * @see LocationPos
 */
public class SceneSize {

    @YamlComment(text = "Rows number")
    public Integer rows;

    @YamlComment(text = "Columns number")
    public Integer cols;

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }
}
