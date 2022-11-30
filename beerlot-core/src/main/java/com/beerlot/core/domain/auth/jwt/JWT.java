package com.beerlot.core.domain.auth.jwt;

import com.beerlot.core.config.JWTConfig;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@Getter
public class JWT {

    private final JWTConfig jwtConfig;

    private String issuer;

    private int accessTokenExpiryInSeconds;

    private int refreshTokenExpiryInSeconds;

    private SignatureAlgorithm algorithm;

    private Key key;

    public JWT(JWTConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.issuer = jwtConfig.getIssuer();
        this.key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes());
        //this.key = Keys.secretKeyFor(jwtConfig.getAlgorithm());
        System.out.println("KEY: " + key.getEncoded());
        this.algorithm = jwtConfig.getAlgorithm();
        this.accessTokenExpiryInSeconds = jwtConfig.getAccessTokenValidityInSeconds();
        this.refreshTokenExpiryInSeconds = jwtConfig.getRefreshTokenValidityInSeconds();
    }

    public String createJWT(String memberId, String email, Date now) {

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuer(issuer);
        jwtBuilder.setIssuedAt(now);
        jwtBuilder.signWith(key, algorithm);

        if(refreshTokenExpiryInSeconds > 0) {
            jwtBuilder.setExpiration(new Date(now.getTime() + refreshTokenExpiryInSeconds));
        }

        jwtBuilder.claim("memberId", memberId);
        jwtBuilder.claim("email", email);

        return jwtBuilder.compact();
    }

    public String createJWT(String email, Date now) {

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuer(issuer);
        jwtBuilder.setIssuedAt(now);
        jwtBuilder.signWith(key, algorithm);

        if(accessTokenExpiryInSeconds > 0) {
            jwtBuilder.setExpiration(new Date(now.getTime() + accessTokenExpiryInSeconds));
        }

        jwtBuilder.claim("email", email);

        return jwtBuilder.compact();
    }

    public Jws<Claims> parseJwt(String jwtString) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwtString);
    }
}
