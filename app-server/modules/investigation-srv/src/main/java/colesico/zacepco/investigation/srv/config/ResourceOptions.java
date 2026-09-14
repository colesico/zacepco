package colesico.zacepco.investigation.srv.config;

import colesico.framework.config.Config;
import colesico.framework.resource.ResourceOptionsPrototype;

@Config
public class ResourceOptions extends ResourceOptionsPrototype {
    @Override
    public void configure(Options options) {
        options.substitution("$investigation", "colesico/zacepco/investigation");
    }
}
