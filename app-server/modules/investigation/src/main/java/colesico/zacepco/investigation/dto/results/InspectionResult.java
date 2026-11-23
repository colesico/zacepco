package colesico.zacepco.investigation.dto.results;

abstract public class InspectionResult {
    public final String code;

    public InspectionResult(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
