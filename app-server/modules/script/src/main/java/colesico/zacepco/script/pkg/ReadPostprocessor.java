package colesico.zacepco.script.pkg;

public interface ReadPostprocessor<D> {
    D process(D document);
}
