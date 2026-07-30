package colesico.zacepco.identity.srv.t9n;

import colesico.framework.translation.Dictionary;
import colesico.framework.translation.assist.lang.Ru;
import colesico.framework.translation.assist.lang.Text;

@Dictionary
public interface UserMessages {

    @Ru("Неверный формат. Можно использовать только латинские буквы, цифры, знак '-'")
    @Text("Invalid format. Only Latin letters, numbers, and the '-' sign are allowed.")
    String invalidUsernameFormat(Object... args);

}
