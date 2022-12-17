package com.beerlot.core.config;

import com.beerlot.core.domain.auth.jwt.JWT;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JWTConfig {

    private String issuer;

    private int accessTokenValidityInSeconds;

    private int refreshTokenValidityInSeconds;

    private String secret;

    private final SignatureAlgorithm algorithm = SignatureAlgorithm.HS512;
}
