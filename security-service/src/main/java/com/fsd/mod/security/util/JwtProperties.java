package com.fsd.mod.security.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String issuer;
    private String secret;
    private long validity;
}
