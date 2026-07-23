package colesico.zacepco.server.ui.config;

import colesico.framework.config.Config;
import colesico.framework.resource.ResourceOptionsPrototype;

@Config
public class ResourceOptions extends ResourceOptionsPrototype {
    @Override
    public void configure(Options options) {
        options.substitution("$server", "colesico/zacepco/server");
    }
}
