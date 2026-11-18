package colesico.zacepco.investigation.dto.action;

public record CluesExhausted() implements LocationSearchResult {

    @Override
    public String code() {
        return "CLUES_EXHAUSTED";
    }

}
