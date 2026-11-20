package colesico.zacepco.investigation.model.state;

public interface Searchable {
    boolean searchAvailable();
    void makeUnsearchable();
}
