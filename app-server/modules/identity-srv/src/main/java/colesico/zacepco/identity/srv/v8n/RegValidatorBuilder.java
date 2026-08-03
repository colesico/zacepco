package colesico.zacepco.identity.srv.v8n;

import colesico.framework.dslvalidator.ValidationContext;
import colesico.framework.dslvalidator.builder.AbstractValidatorBuilder;
import colesico.framework.dslvalidator.t9n.ValidatorMessages;
import colesico.framework.validation.Validator;
import colesico.zacepco.identity.srv.dto.RegUser;
import colesico.zacepco.identity.srv.t9n.RegMessages;
import jakarta.inject.Singleton;

import java.util.function.Predicate;

@Singleton
public class RegValidatorBuilder extends AbstractValidatorBuilder {

    public static final String INVALID_INVITE_CODE = "InvalidInviteCode";
    public static final String USERNAME_ALREADY_EXISTS = "UsernameAlreadyExists";

    private final UserValidatorBuilder userVb;

    private final RegMessages rMessages;

    public RegValidatorBuilder(ValidatorMessages messages, UserValidatorBuilder userVb, RegMessages rMessages) {
        super(messages);
        this.userVb = userVb;
        this.rMessages = rMessages;
    }

    public Validator<RegUser> createRegUserValidator(
            Predicate<ValidationContext<String>> uniqueUsername,
            Predicate<ValidationContext<String>> validInviteCode) {
        return validator(
                field("username", RegUser::getUsername,
                        userVb.validateUsername(),
                        predicate(uniqueUsername, USERNAME_ALREADY_EXISTS, rMessages::usernameAlreadyExists)),
                field("password", RegUser::getPassword,
                        userVb.validatePassword()),
                field("inviteCode", RegUser::getInviteCode,
                        required(),
                        predicate(validInviteCode, INVALID_INVITE_CODE, rMessages::invalidInviteCode))
        );
    }

}
