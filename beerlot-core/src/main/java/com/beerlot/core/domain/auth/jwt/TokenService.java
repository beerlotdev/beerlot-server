package com.beerlot.core.domain.auth.jwt;

import com.beerlot.core.domain.auth.RefreshToken;
import com.beerlot.core.domain.auth.TokenResponse;
import com.beerlot.core.domain.auth.repository.RefreshTokenRepository;
import com.beerlot.core.domain.common.CookieUtil;
import com.beerlot.core.exception.ErrorCode;
import com.beerlot.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JWT jwt;

    public TokenResponse generateToken(Long memberId, String email) {
        String accessToken = generateAccessToken(email);
        String refreshToken = generateRefreshToken(memberId, email);

        refreshTokenRepository.save(new RefreshToken(email, refreshToken));

        return new TokenResponse(accessToken, refreshToken);
    }

    public String generateRefreshToken(Long memberId, String email) {
        String refreshToken = jwt.createJWT(memberId.toString(), email, new Date());
        refreshTokenRepository.save(new RefreshToken(email, refreshToken));
        return refreshToken;
    }

    public String generateAccessToken(String email) {
        return jwt.createJWT(email, new Date());
    }

    public void updateRefreshTokenInCookie(HttpServletRequest request,
                                            HttpServletResponse response,
                                            String name,
                                            String refreshToken) {
        CookieUtil.deleteCookie(request, response, name);
        CookieUtil.addCookie(response, name, refreshToken, jwt.getRefreshTokenExpiryInSeconds());
    }
}
