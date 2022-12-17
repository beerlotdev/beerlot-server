package com.beerlot.core.domain.auth.jwt.service;

import com.beerlot.api.generated.model.AccessTokenResponse;
import com.beerlot.core.domain.auth.AccessTokenResponseHelper;
import com.beerlot.core.domain.auth.jwt.util.JwtUtils;
import com.beerlot.core.domain.auth.jwt.TokenResponse;
import com.beerlot.core.domain.auth.oauth.OAuthUserPrincipal;
import com.beerlot.core.domain.auth.RefreshToken;
import com.beerlot.core.domain.auth.jwt.repository.RefreshTokenRepository;
import com.beerlot.core.domain.auth.util.CookieUtils;
import com.beerlot.core.domain.member.Member;
import com.beerlot.core.domain.member.RoleType;
import com.beerlot.core.domain.member.service.MemberService;
import com.beerlot.core.exception.ErrorMessage;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static com.beerlot.core.domain.auth.util.CookieUtils.COOKIE_NAME_REDIRECT_URL;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberService memberService;
    private final JwtUtils jwtUtils;

    private final long THREE_DAYS_IN_MILLISECOND = 259200000;
    private final int REFRESH_TOKEN_COOKIE_EXPIRY_IN_SECOND = (int) jwtUtils.getRefreshTokenExpiryInMillisecond() / 60000;

    public TokenResponse generateToken(Authentication authentication) {
        OAuthUserPrincipal userPrincipal = (OAuthUserPrincipal) authentication.getPrincipal();

        String oauthId = userPrincipal.getId();
        String email = userPrincipal.getEmail();
        Set<RoleType> roles = userPrincipal.getRoles();

        return new TokenResponse(generateAccessToken(email, roles), generateRefreshToken(oauthId, email, roles));
    }

    public String generateRefreshToken(String oauthId, String email, Set<RoleType> roles) {
        return jwtUtils.createJwt(oauthId, email, roles);
    }

    public String generateAccessToken(String email, Set<RoleType> roles) {
        return jwtUtils.createJwt(null, email, roles);
    }

    public void createRefreshToken(RefreshToken refreshToken) {
        refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findRefreshTokenByOauthId(String oauthId) {
        return refreshTokenRepository.findByOauthId(oauthId);
    }

    public void updateRefreshTokenInCookie(HttpServletRequest request,
                                            HttpServletResponse response,
                                            String name,
                                            String refreshToken) {
        CookieUtils.deleteCookie(request, response, name);
        CookieUtils.addCookie(response, name, refreshToken, REFRESH_TOKEN_COOKIE_EXPIRY_IN_SECOND);
    }

    public AccessTokenResponse refreshTokens(HttpServletRequest request, HttpServletResponse response, String accessToken) {
        String newAccessToken = validateAndGenerateNewAccessToken(accessToken);

        validateAndGenerateNewRefreshToken(request, response);

        return AccessTokenResponseHelper.of(newAccessToken);
        }

    private String validateAndGenerateNewAccessToken(String accessToken) {
        if (!isAccessTokenValidated(accessToken)) {
            throw new IllegalArgumentException(ErrorMessage.TOKEN__NOT_EXPIRED_YET.getMessage());
        }

        String oauthId = jwtUtils.getClaims(accessToken).get("oauthId").toString();
        Member member = memberService.findMemberByOauthId(oauthId)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.MEMBER__NOT_EXIST.getMessage()));

        String newAccessToken = generateAccessToken(member.getEmail(), member.getRoles());
        return newAccessToken;
    }

    private void validateAndGenerateNewRefreshToken(HttpServletRequest request,
                                                      HttpServletResponse response) {
        String refreshToken = CookieUtils.getCookie(request, COOKIE_NAME_REDIRECT_URL)
                .map(Cookie::getValue)
                .orElse(null);

        if (!isRefreshTokenValidated(refreshToken)) {
            throw new IllegalArgumentException(ErrorMessage.TOKEN__INVALID.getMessage());
        }

        Claims claims = jwtUtils.getClaims(refreshToken);
        String oauthId = claims.get("oauthId").toString();

        RefreshToken refreshTokenFromDb = refreshTokenRepository.findByOauthId(oauthId).get();

        Member member = memberService.findMemberByOauthId(oauthId)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.MEMBER__NOT_EXIST.getMessage()));

        if (claims.getExpiration().getTime() - new Date().getTime() <= THREE_DAYS_IN_MILLISECOND) {
            String newRefreshToken = generateRefreshToken(member.getOauthId(), member.getEmail(), member.getRoles());
            refreshTokenFromDb.updateRefreshToken(newRefreshToken);

            CookieUtils.deleteCookie(request, response, COOKIE_NAME_REDIRECT_URL);
            CookieUtils.addCookie(response, COOKIE_NAME_REDIRECT_URL, refreshTokenFromDb.getToken(), REFRESH_TOKEN_COOKIE_EXPIRY_IN_SECOND);
        }
    }

    private boolean isAccessTokenValidated(String accessToken) {
        try {
            Claims claims = jwtUtils.getClaims(accessToken);
            return claims.getExpiration().after(new Date());
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException(ErrorMessage.TOKEN__INVALID.getMessage());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    private boolean isRefreshTokenValidated(String refreshTokenFromCookie) {
        try {
            Claims claims = jwtUtils.getClaims(refreshTokenFromCookie);
            String oauthId = claims.get("oauthId").toString();
            RefreshToken refreshTokenFromDb = refreshTokenRepository.findByOauthId(oauthId)
                    .orElseThrow(() -> new MalformedJwtException(ErrorMessage.TOKEN__INVALID.getMessage()));

            return refreshTokenFromCookie.equals(refreshTokenFromDb.getToken());

        } catch (MalformedJwtException e) {
            throw new MalformedJwtException(ErrorMessage.TOKEN__INVALID.getMessage());
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException(ErrorMessage.TOKEN__EXPIRED.getMessage());
        }
    }
}
