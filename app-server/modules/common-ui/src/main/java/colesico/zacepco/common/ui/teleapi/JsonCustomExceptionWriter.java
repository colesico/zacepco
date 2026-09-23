package colesico.zacepco.common.ui.teleapi;

import colesico.framework.restlet.writer.JsonExceptionWriter;
import colesico.framework.restlet.writer.JsonProblemResultWriter;
import colesico.framework.security.authentication.UnauthenticatedException;
import colesico.framework.security.authorization.UnauthorizedException;
import colesico.framework.telehttp.result.ProblemResult;
import colesico.framework.validation.ValidationException;

public class JsonCustomExceptionWriter extends JsonExceptionWriter {
    public JsonCustomExceptionWriter(JsonProblemResultWriter writer) {
        super(writer);
    }

    @Override
    protected ProblemResult.Builder resultBuilder(Exception exception) {
        return switch (exception) {
            case UnauthenticatedException e -> ProblemResult.exception(exception).status(401);
            case UnauthorizedException e -> ProblemResult.exception(exception).status(401);
            case ValidationException e -> ProblemResult.details(ValidationProblemDetails.of(e)).status(400);
            default -> super.resultBuilder(exception);
        };
    }
}
