package com.beerlot.core.domain.auth.security.jwt.util;

import com.beerlot.core.config.JwtConfig;
import com.beerlot.core.domain.member.RoleType;
import com.beerlot.core.exception.ErrorMessage;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Set;

@Component
@Getter
@Slf4j
public class JwtUtils {

    private final JwtConfig jwtConfig;

    private String issuer;

    private long accessTokenExpiryInMillisecond;

    private long refreshTokenExpiryInMillisecond;

    private SignatureAlgorithm algorithm;

    private Key key;

    public JwtUtils(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.issuer = jwtConfig.getIssuer();
        this.key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes());
        //this.key = Keys.secretKeyFor(jwtConfig.getAlgorithm());
        System.out.println("KEY: " + key.getEncoded());
        this.algorithm = jwtConfig.getAlgorithm();
        this.accessTokenExpiryInMillisecond = jwtConfig.getAccessTokenValidityInMillisecond();
        this.refreshTokenExpiryInMillisecond = jwtConfig.getRefreshTokenValidityInMillisecond();
    }

    public String createJwt(String oauthId, String email, Set<RoleType> roles) { // for accessToken

        Date now = new Date();

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuer(issuer);
        jwtBuilder.setIssuedAt(now);
        jwtBuilder.setSubject(email);
        jwtBuilder.signWith(key, algorithm);

        if(refreshTokenExpiryInMillisecond > 0) {
            jwtBuilder.setExpiration(new Date(now.getTime() + refreshTokenExpiryInMillisecond));
        }

        if (oauthId != null) {
            jwtBuilder.claim("oauthId", oauthId);
        }

        jwtBuilder.claim("roles", roles);

        return jwtBuilder.compact();
    }

    public Claims getClaims(String jwt) {
        try {
            return getJwtParser().parseClaimsJws(jwt).getBody();
        } catch (SecurityException e) {
            log.debug("Invalid JWT Signature.");
            throw new MalformedJwtException(ErrorMessage.TOKEN__INVALID.getMessage());
        } catch (MalformedJwtException e) {
            log.debug("Invalid JWT token.");
            throw new MalformedJwtException(ErrorMessage.TOKEN__INVALID.getMessage());
        } catch (ExpiredJwtException e) {
            log.debug("Expired JWT token.");
            throw new MalformedJwtException(ErrorMessage.TOKEN__INVALID.getMessage());
        } catch (UnsupportedJwtException e) {
            log.debug("Unsupported JWT token.");
            throw new MalformedJwtException(ErrorMessage.TOKEN__INVALID.getMessage());
        } catch (IllegalArgumentException e) {
            log.debug("JWT token compact of handler are invalid.");
            throw new MalformedJwtException(ErrorMessage.TOKEN__INVALID.getMessage());
        }
    }

    private JwtParser getJwtParser() {
        return Jwts.parserBuilder().setSigningKey(key).build();
    }
}
