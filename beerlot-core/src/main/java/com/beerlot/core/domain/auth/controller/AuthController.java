package com.beerlot.core.domain.auth.controller;

import com.beerlot.api.generated.model.MemberCreateRequest;
import com.beerlot.core.domain.auth.dto.response.AccessTokenResponse;
import com.beerlot.core.domain.auth.security.jwt.service.TokenService;
import com.beerlot.core.domain.auth.security.oauth.entity.OAuthUserPrincipal;
import com.beerlot.core.domain.auth.util.HeaderUtils;
import com.beerlot.core.domain.member.Member;
import com.beerlot.core.domain.member.service.MemberService;
import com.beerlot.core.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@Slf4j
public class AuthController implements AuthApi {

    private final MemberService memberService;
    private final TokenService tokenService;

    @Override
    @PreAuthorize("hasRole('ROLE_GENERAL')")
    public ResponseEntity<AccessTokenResponse> refreshToken(HttpServletRequest request,
                                                            HttpServletResponse response,
                                                            String bearerToken) {
        String accessToken = HeaderUtils.getAccessToken(bearerToken);
        return new ResponseEntity<>(tokenService.refreshTokens(request, response, accessToken), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<Void> signUp(MemberCreateRequest memberCreateRequest) {

        Member member = getAuthenticatedMember();
        try {
            memberService.signUpMember(member, memberCreateRequest);
        } catch (NoSuchElementException e) {
            log.debug(e.getMessage());
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    private Member getAuthenticatedMember() {
        String oauthId = ((OAuthUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getOauthId();

        return memberService.findMemberByOauthId(oauthId).orElseThrow
                (() -> new NoSuchElementException(ErrorMessage.MEMBER__NOT_EXIST.getMessage()));
    }
}
