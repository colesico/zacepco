package colesico.zacepco.script.lib.model.entity;

import java.util.Objects;

/**
 * Grid position
 * @see SceneSize
 */
public class LocationPos {

    /**
     * Column number
     */
    public Integer col;

    /**
     * Row number
     */
    public Integer row;

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LocationPos pos = (LocationPos) o;
        return Objects.equals(col, pos.col) && Objects.equals(row, pos.row);
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }

    @Override
    public String toString() {
        return "Position{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }
}
