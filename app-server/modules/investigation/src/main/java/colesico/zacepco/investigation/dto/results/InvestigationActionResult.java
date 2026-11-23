package colesico.zacepco.investigation.dto.results;

abstract public class InvestigationActionResult {
    public final String code;

    public InvestigationActionResult(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
