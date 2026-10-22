package colesico.zacepco.respack.model.script;

import colesico.zacepco.respack.yaml.YamlComment;

/**
 * Scene size
 * @see LocationPos
 */
public class SceneSize {

    @YamlComment(text = "Rows number", inline = true)
    public Integer rows;

    @YamlComment(text = "Columns number", inline = true)
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
