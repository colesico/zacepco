package colesico.zacepco.identity.srv.v8n;

import colesico.framework.dslvalidator.Command;
import colesico.framework.dslvalidator.builder.AbstractValidatorBuilder;
import colesico.framework.dslvalidator.command.LengthVerifier;
import colesico.framework.dslvalidator.command.RequiredVerifier;
import colesico.framework.dslvalidator.t9n.ValidatorMessages;
import colesico.framework.validation.Validator;
import colesico.zacepco.identity.srv.dto.CreateUser;
import colesico.zacepco.identity.srv.t9n.UserMessages;
import jakarta.inject.Singleton;

@Singleton
public class UserValidatorBuilder extends AbstractValidatorBuilder {

    public static final String USERNAME_PATTERN = "^[a-zA-Z0-9_]+$";

    private final UserMessages uMessages;

    public UserValidatorBuilder(ValidatorMessages messages, UserMessages uMessages) {
        super(messages);
        this.uMessages = uMessages;
    }

    protected Command<String> validatePassword() {
        return chain(
                new RequiredVerifier(messages),
                new LengthVerifier(6, 32, messages)
        );
    }

    public Command<String> validateUserName() {
        return chain(
                required(),
                regexp(USERNAME_PATTERN, "InvalidNameFormat", uMessages::invalidUsernameFormat),
                length(4, 16)
        );
    }

    public Validator<CreateUser> createUserValidator() {
        return validator(
                field("username", CreateUser::getUsername, validateUserName()),
                field("password", CreateUser::getPassword, validatePassword())
        );
    }

}
