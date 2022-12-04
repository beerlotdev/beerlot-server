package com.beerlot.core.domain.auth.service;

import com.beerlot.api.generated.model.MemberCreateRequest;
import com.beerlot.core.domain.auth.jwt.TokenService;
import com.beerlot.core.domain.auth.oauth.GoogleOAuthTokenResponse;
import com.beerlot.core.domain.auth.oauth.OAuthProvider;
import com.beerlot.core.domain.auth.oauth.OAuthUser;
import com.beerlot.core.domain.member.Member;
import com.beerlot.core.domain.member.service.MemberService;
import com.beerlot.core.exception.ConflictException;
import com.beerlot.core.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private MemberService memberService;

    @Autowired
    private TokenService tokenService;

    private final String APP_REDIRECT_AFTER_LOGIN = "https://beerlot-client.vercel.app";
    private final String APP_REDIRECT_SIGNUP = "https://beerlot-client.vercel.app/signup";
    private final String REFRESH_TOKEN = "beerlot_refresh_token";

    public String redirect(OAuthProvider oAuthProvider) {
        return oAuthProvider.buildLoginRequestUrl();
    }

    public String login(HttpServletRequest request, HttpServletResponse response, OAuthProvider oAuthProvider, String code) {
        GoogleOAuthTokenResponse googleOAuthTokenResponse = oAuthProvider.requestToken(code);

        OAuthUser oAuthUser = oAuthProvider.requestUserInformation(googleOAuthTokenResponse.getAccessToken());

        Optional<Member> memberOptional = memberService.findMemberByEmail(oAuthUser.getEmail());

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            // STEP1: Generate access token
            String accessToken = tokenService.generateAccessToken(member.getEmail());

            // STEP2: Generate refresh token and save into DB
            String refreshToken = tokenService.generateRefreshToken(member.getId(), member.getEmail());

            // STEP3: Add the refresh token in cookie
            tokenService.updateRefreshTokenInCookie(request, response, REFRESH_TOKEN, refreshToken);

            return APP_REDIRECT_AFTER_LOGIN + "?access_token=" + accessToken;
        } else {
            return APP_REDIRECT_SIGNUP;
        }
    }

    public String signUp(HttpServletRequest request, HttpServletResponse response, MemberCreateRequest memberCreateRequest) {
        // STEP1: Check if member exists with email
        Optional<Member> memberOptional = memberService.findMemberByEmail(memberCreateRequest.getEmail());

        if (memberOptional.isPresent()) {
            throw new ConflictException(ErrorCode.MEMBER_ALREADY_EXIST);
        } else {
            // STEP2: If not exist, create member
            Member member = memberService.createMember(memberCreateRequest);

            // STEP3: Generate access token
            String accessToken = tokenService.generateAccessToken(member.getEmail());

            // STEP4: Generate refresh token and save into DB
            String refreshToken = tokenService.generateRefreshToken(member.getId(), member.getEmail());

            // STEP3: Add the refresh token in cookie
            tokenService.updateRefreshTokenInCookie(request, response, REFRESH_TOKEN, refreshToken);

            return APP_REDIRECT_AFTER_LOGIN + "?access_token=" + accessToken;
        }
    }
}
