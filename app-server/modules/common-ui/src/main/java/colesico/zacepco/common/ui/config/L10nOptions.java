package colesico.zacepco.common.ui.config;

import colesico.framework.config.Config;
import colesico.framework.resource.l10n.L10nOptionsPrototype;

@Config
public class L10nOptions extends L10nOptionsPrototype {

    @Override
    public void configure(Options options) {
        options.baseName("colesico/zacepco/common/ui/t9n/Strings{Q}")
                    .qualifiers().language("ru")
               .baseName("colesico/zacepco/common/ui/t9n/Errors{Q}")
                    .qualifiers().language("ru");
    }
}
