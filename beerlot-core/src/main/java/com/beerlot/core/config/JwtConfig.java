package com.beerlot.core.config;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    private String issuer;

    private long accessTokenValidityInMillisecond;

    private long refreshTokenValidityInMillisecond;

    private String secret;

    private final SignatureAlgorithm algorithm = SignatureAlgorithm.HS512;
}
