package colesico.zacepco.common.ui.teleapi;

import colesico.framework.teleapi.assist.TeleProblem;
import colesico.framework.validation.ValidationException;
import colesico.framework.validation.ValidationIssue;

public class ValidationProblemDetails extends TeleProblem.ProblemDetails {

    protected final ValidationIssue issue;

    public ValidationProblemDetails(String type, String message, ValidationIssue issue) {
        super(type, message);
        this.issue = issue;
    }

    public static ValidationProblemDetails of(ValidationException exception) {
        return new ValidationProblemDetails(ValidationException.class.getCanonicalName(),
                exception.getMessage(),
                exception.issue());
    }

    public ValidationIssue getIssue() {
        return issue;
    }

    public ValidationIssue issue() {
        return issue;
    }
}
