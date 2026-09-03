package colesico.zacepco.identity.srv.validation;

import colesico.framework.dslvalidator.builder.AbstractValidatorBuilder;
import colesico.framework.dslvalidator.t9n.ValidatorMessages;
import colesico.framework.validation.Validator;
import colesico.zacepco.identity.srv.dto.AuthUser;
import jakarta.inject.Singleton;

@Singleton
public class AuthValidatorBuilder extends AbstractValidatorBuilder {
    public AuthValidatorBuilder(ValidatorMessages messages) {
        super(messages);
    }

    public Validator<AuthUser> createAuthUserValidator() {
        return validator(
                field("username", AuthUser::getUsername,
                        required()
                ),
                field("password", AuthUser::getPassword,
                        required()
                )
        );
    }
}
