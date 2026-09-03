package colesico.zacepco.common.ui.config;

import colesico.framework.config.Config;
import colesico.framework.jjwt.JwtConfigPrototype;

@Config
public class JwtConfig extends JwtConfigPrototype {

    @Override
    public String accessSecret() {
        return "********************************";
    }

    @Override
    public String refreshSecret() {
        return "********************************";
    }

    @Override
    public String loginUrl() {
        return "/identity/login";
    }
}
