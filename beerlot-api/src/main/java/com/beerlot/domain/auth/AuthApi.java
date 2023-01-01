package com.beerlot.domain.auth;

import com.beerlot.annotation.CurrentUser;
import com.beerlot.domain.auth.dto.response.AccessTokenResponse;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.dto.request.MemberRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/api/v1/auth")
public interface AuthApi {

    @PatchMapping("/signup")
    //@PreAuthorize("hasRole('GUEST')")
    ResponseEntity<Void> signUp(@CurrentUser Member member, @RequestBody MemberRequest memberRequest);

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('GUEST')")
    ResponseEntity<AccessTokenResponse> refreshToken(HttpServletRequest request,
                                                     HttpServletResponse response,
                                                     @CurrentUser Member member,
                                                     @RequestHeader("Authorization") String bearerToken);
}
