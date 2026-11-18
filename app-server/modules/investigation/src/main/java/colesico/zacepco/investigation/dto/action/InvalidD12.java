package colesico.zacepco.investigation.dto.action;

public record InvalidD12() implements LocationSearchResult {
    @Override
    public String code() {
        return "INVALID_D12";
    }
}
