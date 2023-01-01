package com.beerlot.domain.auth;

import com.beerlot.domain.auth.dto.response.AccessTokenResponse;
import com.beerlot.domain.auth.security.jwt.service.TokenService;
import com.beerlot.domain.auth.security.oauth.entity.OAuthUserPrincipal;
import com.beerlot.domain.auth.util.HeaderUtils;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.dto.request.MemberRequest;
import com.beerlot.domain.member.service.MemberService;
import com.beerlot.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController implements AuthApi {

    private final MemberService memberService;
    private final TokenService tokenService;

    @Override
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<AccessTokenResponse> refreshToken (HttpServletRequest request,
                                                            HttpServletResponse response,
                                                            Member member,
                                                            String bearerToken) {
        String accessToken = HeaderUtils.getAccessToken(bearerToken);
        return new ResponseEntity<>(tokenService.refreshTokens(request, response, accessToken), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<Void> signUp (Member member, MemberRequest memberRequest) {
        memberService.signUpMember(member, memberRequest);
        return new ResponseEntity(HttpStatus.OK);

    }

    private Member getAuthenticatedMember() {
        String oauthId = ((OAuthUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getOauthId();

        return memberService.findMemberByOauthId(oauthId).orElseThrow
                (() -> new NoSuchElementException(ErrorMessage.MEMBER__NOT_EXIST.getMessage()));
    }
}
