package colesico.zacepco.ui.config;

import colesico.framework.config.Config;
import colesico.framework.resource.ResourceOptionsPrototype;

@Config
public class ResourceOptions extends ResourceOptionsPrototype {
    @Override
    public void configure(Options options) {
        options.substitution("$ui", "colesico/zacepco/ui");
    }
}
