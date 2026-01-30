package colesico.zacepco.script.lib.model;

import java.util.List;

public class Location {
    public Integer id;
    public String name;
    public List<Integer> passages;

    /**
     * Hidden location or not
     */
    public Boolean hidden;
}
