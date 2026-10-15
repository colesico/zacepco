package colesico.zacepco.casereg.ui.dto;

import java.util.ArrayList;
import java.util.List;

public class SceneOverview {

    public Integer rows;
    public Integer cols;

    public List<EntityOverview> locations = new ArrayList<>();


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

    public List<EntityOverview> getLocations() {
        return locations;
    }

    public void setLocations(List<EntityOverview> locations) {
        this.locations = locations;
    }
}
