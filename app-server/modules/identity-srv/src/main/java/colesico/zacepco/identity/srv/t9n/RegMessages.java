package colesico.zacepco.identity.srv.t9n;

import colesico.framework.translation.Dictionary;
import colesico.framework.translation.assist.lang.Ru;
import colesico.framework.translation.assist.lang.Text;

@Dictionary
public interface RegMessages {

    @Ru("Пользователь с таким именем уже существует")
    @Text("Username is already taken")
    String usernameAlreadyExists(Object... args);


    @Ru("Неверный инвайт код")
    @Text("Invalid invite code")
    String invalidInviteCode(Object... args);

}
