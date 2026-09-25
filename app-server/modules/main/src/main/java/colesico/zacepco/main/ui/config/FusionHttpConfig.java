package colesico.zacepco.main.ui.config;

import colesico.framework.config.Config;
import colesico.framework.fusionhttp.FusionHttpConfigPrototype;
import io.fusionauth.http.io.MultipartFileUploadPolicy;
import io.fusionauth.http.server.HTTPListenerConfiguration;
import io.fusionauth.http.server.HTTPServerConfiguration;

@Config
public class FusionHttpConfig extends FusionHttpConfigPrototype {
    @Override
    public void applyConfiguration(HTTPServerConfiguration config) {
        config.withListener(new HTTPListenerConfiguration(8080))
                .getMultipartConfiguration()
                .withFileUploadPolicy(MultipartFileUploadPolicy.Allow)
                .withDeleteTemporaryFiles(true)
                .withMaxFileSize(10)
                .withMaxRequestSize(50);
    }
}
