package colesico.zacepco.identity.ui.t9n;

import colesico.framework.config.Config;
import colesico.framework.resource.l10n.L10nOptionsPrototype;

@Config
public class L10nOptions extends L10nOptionsPrototype {

    @Override
    public void configure(Options options) {
        options.baseName("colesico/zacepco/identity/ui/t9n/Login{Q}")
                    .qualifiers().language("ru")
               .baseName("colesico/zacepco/identity/ui/t9n/SignUp{Q}")
                    .qualifiers().language("ru");
    }
}
