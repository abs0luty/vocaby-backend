package org.vocaby.backend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("application.access-token")
public class AccessTokenConfiguration {

    private String secretKey;
    private long lifetimeInSeconds;
}
