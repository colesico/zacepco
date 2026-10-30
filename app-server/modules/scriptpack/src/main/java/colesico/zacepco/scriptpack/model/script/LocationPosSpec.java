package colesico.zacepco.scriptpack.model.script;

import java.util.Objects;

/**
 * Scene grid  location position
 * @see SceneSizeSpec
 */
public final class LocationPosSpec {

    /**
     * Column number
     */
    public Integer col;

    /**
     * Row number
     */
    public Integer row;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LocationPosSpec pos = (LocationPosSpec) o;
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
