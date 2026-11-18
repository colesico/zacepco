package colesico.zacepco.investigation.dto.action;

public record InsufficientBudget() implements LocationSearchResult {

    @Override
    public String code() {
        return "INSUFFICIENT_BUDGET";
    }

}
